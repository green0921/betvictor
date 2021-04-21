package com.betvictor.processing.service;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.ParagraphResponse;

public interface ParagraphService {

    ParagraphResponse serveParagraphRequest(ParagraphRequest paragraphRequest);
}
