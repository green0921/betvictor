package com.betvictor.testUtilities;

import com.betvictor.dto.History;

public class TestUtils {
    private TestUtils() {
    }

    public static History createHistory() {
        History history = new History();
        history.setFrequentWord("something");
        history.setAverageParagraphSize("3");
        history.setAverageParagraphProcessingTime("1.0000 s");
        history.setTotalProcessingTime("4.0000 s");
        return history;
    }
}