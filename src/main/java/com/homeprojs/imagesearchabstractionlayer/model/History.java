package com.homeprojs.imagesearchabstractionlayer.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yao Zhang
 * @date 2023-12-13
 * # @apiNote
 */
public class History {
    private String searchTerm;
    private String timestamp;

    public History(String searchTerm) {
        this.searchTerm = searchTerm;
        this.timestamp = generateTimeStamp();
    }

    public History(String searchTerm, String timestamp) {
        this.searchTerm = searchTerm;
        this.timestamp = timestamp;
    }

    private String generateTimeStamp() {
        return ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss"));
    }

    @Override
    public String toString() {
        return "History{" + "searchTerm='" + searchTerm + '\'' + ", timestamp='" + timestamp + '\'' + '}';
    }

    public String toOneLineLog() {
        return String.format("%s, %s", searchTerm, timestamp);
    }
}
