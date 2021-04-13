package com.betvictor.service;

import com.betvictor.model.ParagraphRequest;
import com.betvictor.model.StreamData;

public interface RandomTextProcessor {

    StreamData processRandomTexts(ParagraphRequest paragraphRequest);
}
