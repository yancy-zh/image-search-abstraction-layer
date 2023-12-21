package com.homeprojs.imagesearchabstractionlayer.model;


import com.homeprojs.imagesearchabstractionlayer.exception.RecentSearchTermsIOException;
import com.homeprojs.imagesearchabstractionlayer.exception.UserFileNotFoundException;

import java.io.*;
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
    private static String recentSearchTermsFileStr = "recentSearchStrs.txt";
    Logger logger = Logger.getLogger(RecentActivity.class.getSimpleName());
    File recentSearchTermsFile = new File(recentSearchTermsFileStr);
    private Queue<String> historyQueue;
    BufferedWriter out;
    BufferedReader in;

    public RecentActivity() {
        try {
            if (recentSearchTermsFile.createNewFile()) {
                logger.info(String.format("%s file already exists", recentSearchTermsFileStr));
            }
            // if file already exists will do nothing
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
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
        Pattern re = Pattern.compile("[a-z|A-Z|\\s]+, [\\d|.]+");
        Matcher matcher = re.matcher(str);
        return matcher.matches();
    }


    public List<String> getRecentTerms() {
        List<History> historys = new ArrayList<>();
        Queue<String> uniqueTermsQueue = new LinkedList<>();
        HashMap<String, History> map = new HashMap<>();
        List<String> terms = new ArrayList<>();
        try {
            in = new BufferedReader(new FileReader(recentSearchTermsFile));
            String line = in.readLine();
            while (line != null) {
                if (!patternChk(line)) {
                    continue;
                }
                List<String> words = Stream.of(line.split(",")).collect(Collectors.toList());
                History hist = new History(words.get(0), words.get(1));
                terms.add(words.get(0));
                historys.add(hist);
                line = in.readLine();
            }
            return terms;
        } catch (FileNotFoundException e) {
            throw new UserFileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new RecentSearchTermsIOException(e.getMessage());
        }
    }
}
