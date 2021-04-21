package com.betvictor.processing.controller;

import com.betvictor.processing.model.ParagraphRequest;
import com.betvictor.processing.model.ParagraphResponse;
import com.betvictor.processing.service.RequestValidator;
import com.betvictor.processing.service.ParagraphService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.betvictor.processing.utilites.Constants.TOPIC;

@Slf4j
@RestController
public class ParagraphController {

    @Autowired
    private RequestValidator requestValidator;
    @Autowired
    private ParagraphService paragraphService;
    @Autowired
    private KafkaTemplate<String, ParagraphResponse> kafkaTemplate;


    @GetMapping(value = "/betvictor/text")
    @ResponseBody
    public ParagraphResponse getParagraph(@SpringQueryMap ParagraphRequest paragraphRequest) {
        requestValidator.validateParagraphRequest(paragraphRequest);
        log.info(paragraphRequest.toString());
        ParagraphResponse paragraphResponse = paragraphService.serveParagraphRequest(paragraphRequest);
        kafkaTemplate.send(TOPIC, paragraphResponse);
        return paragraphResponse;
    }
}
