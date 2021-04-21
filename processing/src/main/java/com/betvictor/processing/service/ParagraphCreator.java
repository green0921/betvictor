package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.model.StreamData;

public interface ParagraphCreator {

    ParagraphResponse createParagraphResponse(StreamData streamData, long requestNumbers, double currentProcessingTime);
}
