package com.betvictor.service;

import com.betvictor.model.ParagraphResponse;
import com.betvictor.model.StreamData;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.betvictor.utilites.Constants.SECOND_FORMAT;
import static com.betvictor.utilites.Constants.ZERO;

@Service
public class ParagraphCreatorImpl implements ParagraphCreator {

    @Override
    public ParagraphResponse createParagraphResponse(StreamData streamData, long numberOfRequests, double totalProcessingTime) {
        BigInteger averageParagraphSize = getAverageParagraphSizeIfNotZero(streamData);
        double averageParagraphProcessingTime = getAverageParagraphProcessingTimeIfNotZero(numberOfRequests, totalProcessingTime);
        return new ParagraphResponse(
                streamData.getFrequentWord().getKey(),
                averageParagraphSize.toString(),
                String.format(SECOND_FORMAT, averageParagraphProcessingTime),
                String.format(SECOND_FORMAT, totalProcessingTime));
    }

    private BigInteger getAverageParagraphSizeIfNotZero(StreamData streamData){
        return !streamData.getParagraphSizeOfSum().equals(BigInteger.ZERO)
                ? streamData.getWordsNumberOfSum().divide(streamData.getParagraphSizeOfSum())
                : BigInteger.ZERO;
    }

    private double getAverageParagraphProcessingTimeIfNotZero(long numberOfRequests, double totalProcessingTime){
        return numberOfRequests != ZERO
                ? totalProcessingTime / numberOfRequests
                : ZERO;
    }
}
