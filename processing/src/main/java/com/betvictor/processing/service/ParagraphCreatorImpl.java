package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.model.StreamData;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static com.betvictor.processing.utilites.Constants.SECOND_FORMAT;
import static com.betvictor.processing.utilites.Constants.ZERO;

@Service
public class ParagraphCreatorImpl implements ParagraphCreator {

    @Override
    public ParagraphResponse createParagraphResponse(StreamData streamData, long numberOfRequests, double totalProcessingTime) {
        int averageParagraphSize = getAverageParagraphSizeIfNotZero(streamData);
        double averageParagraphProcessingTime = getAverageParagraphProcessingTimeIfNotZero(numberOfRequests, totalProcessingTime);
        return new ParagraphResponse(
                streamData.getFrequentWord().getKey(),
                String.valueOf(averageParagraphSize),
                String.format(SECOND_FORMAT, averageParagraphProcessingTime),
                String.format(SECOND_FORMAT, totalProcessingTime));
    }

    private int getAverageParagraphSizeIfNotZero(StreamData streamData){
        return streamData.getParagraphSizeOfSum().get() != ZERO
                ? streamData.getWordsNumberOfSum().get() / streamData.getParagraphSizeOfSum().get()
                : ZERO;
    }

    private double getAverageParagraphProcessingTimeIfNotZero(long numberOfRequests, double totalProcessingTime){
        return numberOfRequests != ZERO
                ? totalProcessingTime / numberOfRequests
                : ZERO;
    }
}
