package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.StreamData;

public interface RandomTextProcessor {

    StreamData processRandomTexts(ParagraphRequest paragraphRequest);
}
