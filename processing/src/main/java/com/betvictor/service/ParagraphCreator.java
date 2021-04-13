package com.betvictor.service;

import com.betvictor.model.ParagraphResponse;
import com.betvictor.model.StreamData;

public interface ParagraphCreator {

    ParagraphResponse createParagraphResponse(StreamData streamData, long requestNumbers, double currentProcessingTime);
}
