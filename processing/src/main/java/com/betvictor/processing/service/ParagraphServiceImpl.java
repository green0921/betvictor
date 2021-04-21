package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.model.StreamData;
import com.betvictor.processing.utilites.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.betvictor.processing.utilites.Constants.ONE;

@Service
public class ParagraphServiceImpl implements ParagraphService {


    @Autowired
    private ParagraphCreator paragraphCreator;
    @Autowired
    private RandomTextProcessor randomTextProcessor;
    @Autowired
    private TimeUtil timeUtil;

    @Override
    public ParagraphResponse serveParagraphRequest(ParagraphRequest paragraphRequest) {
        long numberOfRequests = paragraphRequest.getP_end() - paragraphRequest.getP_start() + ONE;
        timeUtil.startStopWatch();
        StreamData streamData = randomTextProcessor.processRandomTexts(paragraphRequest);
        return paragraphCreator.createParagraphResponse(streamData, numberOfRequests, timeUtil.getElapsedTimeInSeconds());
    }
}
