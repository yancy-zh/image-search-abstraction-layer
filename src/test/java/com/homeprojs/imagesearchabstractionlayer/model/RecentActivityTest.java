package com.homeprojs.imagesearchabstractionlayer.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Yao Zhang
 * @date 2023-12-25
 * # @apiNote
 */
class RecentActivityTest {

    @Test
    void testGetRecentTerms() {
        RecentActivity recentActivity = new RecentActivity();
        String testStr = "huawei mate60,\n" +
                "macbook pro,  \n" +
                "tesla model y,\n" +
                "bill gates,   \n" +
                "Barack Obama, \n" +
                "Elon Musk,    \n" +
                "Anne Hathway, \n" +
                "LinkedIn,     \n" +
                "OpenAI,       \n" +
                "Google Gemini";
        List<String> ls_str = Arrays.stream(testStr.split(",")).map(String::trim).collect(Collectors.toList());
        assertEquals(ls_str, recentActivity.getRecentTerms());
    }

    @Test
    void testUseExistingLog1() {
        RecentActivity recentActivity = new RecentActivity("testLog1.txt");
        String testStr =
                "bill gates,   \n" +
                        "Barack Obama, \n" +
                        "Elon Musk,    \n" +
                        "Anne Hathway, \n" +
                        "LinkedIn,     \n" +
                        "OpenAI,       \n" +
                        "Google Gemini,\n" +
                        "DigitalOcean,\n" +
                        "mettl,\n" +
                        "Overflowstackoverflow";
        List<String> ls_str = Arrays.stream(testStr.split(",")).map(String::trim).collect(Collectors.toList());
        assertEquals(ls_str, recentActivity.getRecentTerms());
    }

    @Test
    void testUseExistingLog2() {
        RecentActivity recentActivity = new RecentActivity("testLog2.txt");
        String testStr =
                "DigitalOcean,\n" +
                        "mettl,\n" +
                        "Overflowstackoverflow,\n" +
                        "battery,\n" +
                        "Calender,\n" +
                        "ear phone,\n" +
                        "schnitzel plate,\n" +
                        "stainless steel basin,\n" +
                        "terence piano,\n" +
                        "hlk milk blender\n";
        List<String> ls_str = Arrays.stream(testStr.split(",")).map(String::trim).collect(Collectors.toList());
        assertEquals(ls_str, recentActivity.getRecentTerms());
    }
}