package com.homeprojs.imagesearchabstractionlayer;

import com.homeprojs.imagesearchabstractionlayer.model.RecentActivity;
import org.junit.jupiter.api.Test;

/**
 * @author Yao Zhang
 * @date 2023-12-12
 * # @apiNote
 */
class WriterReaderTest {

    @Test
    void testcaseSearchString() {
        String[] lsStr = new String[]{"huawei mate60", "macbook pro", "tesla model y", "bill gates",
                "Barack Obama", "Elon Musk", "Anne Hathway", "LinkedIn", "OpenAI", "Google Gemini"};
        RecentActivity recentActivity = new RecentActivity();
        recentActivity.deleteFile();
        recentActivity.openWriterStream();
        for (String str : lsStr) {
            recentActivity.saveSearchString(str);
            recentActivity.appendNewLine();
        }
        recentActivity.closeWriter();
    }


}