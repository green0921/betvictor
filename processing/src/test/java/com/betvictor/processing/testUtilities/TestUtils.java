package com.betvictor.processing.testUtilities;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.model.StreamData;

import java.math.BigInteger;
import java.util.Map;

public class TestUtils {
    private TestUtils() {
    }

    public static ParagraphRequest createParagraphRequest(int pStart, int pEnd, int wCountMin, int wCountMax) {
        ParagraphRequest paragraphRequest = new ParagraphRequest();
        paragraphRequest.setP_start(pStart);
        paragraphRequest.setP_end(pEnd);
        paragraphRequest.setW_count_min(wCountMin);
        paragraphRequest.setW_count_max(wCountMax);
        return paragraphRequest;
    }

    public static StreamData createStreamData(Map.Entry<String, Integer> frequentWord, BigInteger paragraphSizeOfSum, BigInteger wordsNumberOfSum) {
        StreamData streamData = new StreamData();
        streamData.setFrequentWord(frequentWord);
        streamData.setParagraphSizeOfSum(paragraphSizeOfSum);
        streamData.setWordsNumberOfSum(wordsNumberOfSum);
        return streamData;
    }

    public static ParagraphResponse createParagraphResponse(String frequentWord, String averageParagraphSize,
                                                            String averageParagraphProcessingTime , String totalProcessingTime) {
        return new ParagraphResponse(frequentWord, averageParagraphSize, averageParagraphProcessingTime, totalProcessingTime);
    }

    public static String[] createWords(String... words) {
        return words;
    }
}