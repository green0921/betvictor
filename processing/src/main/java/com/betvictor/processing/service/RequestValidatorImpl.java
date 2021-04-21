package com.betvictor.processing.service;

import com.betvictor.processing.exception.RequestException;
import com.betvictor.processing.model.ParagraphRequest;
import org.springframework.stereotype.Service;

@Service
public class RequestValidatorImpl implements RequestValidator {

    @Override
    public void validateParagraphRequest(ParagraphRequest paragraphRequest) {
        if(paragraphRequest.getP_start() > paragraphRequest.getP_end()) {
            throw new RequestException("Start number of paragraphs can't be bigger than end number of paragraphs!");
        }
        if(paragraphRequest.getW_count_min() > paragraphRequest.getW_count_max()) {
            throw new RequestException("Min number of words can't be bigger than max number of paragraphs!");
        }
        if(paragraphRequest.getP_start() < 0 || paragraphRequest.getP_end() < 0 ||
                paragraphRequest.getW_count_min() < 0 || paragraphRequest.getW_count_max() < 0 ) {
            throw new RequestException("Request parameters cannot be negative!");
        }
    }
}
