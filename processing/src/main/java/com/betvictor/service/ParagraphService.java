package com.betvictor.service;

import com.betvictor.model.ParagraphRequest;
import com.betvictor.model.ParagraphResponse;

public interface ParagraphService {

    ParagraphResponse serveParagraphRequest(ParagraphRequest paragraphRequest);
}
