package com.homeprojs.imagesearchabstractionlayer.model;


import com.homeprojs.imagesearchabstractionlayer.exception.RecentSearchTermsIOException;
import com.homeprojs.imagesearchabstractionlayer.exception.UserFileNotFoundException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yao Zhang
 * @date 2023-12-12
 * # @apiNote
 */
public class RecentActivity {


    private String recentSearchTermsFileStr = "recentSearchStrs.txt";
    private static String dateTimePattern = "yyyy.MM.dd.HH.mm.ss";
    Logger logger = Logger.getLogger(RecentActivity.class.getSimpleName());
    File recentSearchTermsFile;
    private int QUEUE_MAX_SIZE = 10;
    BufferedWriter out;
    BufferedReader in;

    public RecentActivity() {
        recentSearchTermsFile = new File(recentSearchTermsFileStr);
        try {
            if (recentSearchTermsFile.createNewFile()) {
                logger.info(String.format("%s file already exists", recentSearchTermsFileStr));
            }
            // if file already exists will do nothing
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }

    public RecentActivity(String p_recentSearchTermsFileStr) {
        recentSearchTermsFileStr = p_recentSearchTermsFileStr;
        recentSearchTermsFile = new File(recentSearchTermsFileStr);
    }

    public void openWriterStream() {
        try {
            out = new BufferedWriter(new FileWriter(recentSearchTermsFileStr, true));
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }

    public void saveSearchString(String searchStr) {
        try {
            History history = new History(searchStr);
            out.write(history.toOneLineLog());
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }

    public void appendNewLine() {
        try {
            out.newLine();
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }

    public void closeWriter() {
        try {
            out.close();
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }

    public boolean deleteFile() {
        return recentSearchTermsFile.delete();
    }

    private boolean patternChk(String str) {
        Pattern re = Pattern.compile("^[\\w|\\s?]+, [0-9|.?]+$");
        Matcher matcher = re.matcher(str);
        return matcher.matches();
    }


    public List<String> getRecentTerms() {
        Queue<String> uniqueTermsQueue = new LinkedList<>();
        HashMap<String, String> map = new HashMap<>();
        try {
            in = new BufferedReader(new FileReader(recentSearchTermsFile));
            String line = in.readLine();
            while (line != null) {
                if (!patternChk(line)) {
                    continue;
                }
                List<String> words = Stream.of(line.split(",")).collect(Collectors.toList());
                History hist = new History(words.get(0), words.get(1).trim());
                if (map.containsKey(hist.getSearchTerm())) {
                    // update timestamp for this key
                    map.put(hist.getSearchTerm(), hist.getTimestamp());
                } else {
                    if (uniqueTermsQueue.size() == QUEUE_MAX_SIZE) {
                        uniqueTermsQueue.poll();
                    }
                    if (map.size() == QUEUE_MAX_SIZE) {
                        // find the pair with the earliest timestamp
                        map.remove(getEarliestTerm(map));
                    }
                    map.put(hist.getSearchTerm(), hist.getTimestamp());
                    uniqueTermsQueue.add(hist.getSearchTerm());
                }
                line = in.readLine();
            }
            return Stream.of(uniqueTermsQueue.toArray()).map(Object::toString).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new UserFileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }

    private String getEarliestTerm(HashMap<String, String> map) {
        ArrayList<LocalDateTime> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(stringToTimestamp(entry.getValue()));
        }
        Collections.sort(list);
        String timeStampEarliestTerm = timestampToString(list.get(0));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (timeStampEarliestTerm.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private LocalDateTime stringToTimestamp(String value) {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(dateTimePattern));
    }

    private String timestampToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(dateTimePattern));
    }
}
