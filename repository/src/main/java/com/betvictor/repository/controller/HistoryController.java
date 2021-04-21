package com.betvictor.repository.controller;

import com.betvictor.repository.dto.History;
import com.betvictor.repository.service.HistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(value ="/betvictor/history")
    @ResponseBody
    public List<History> getHistory(){
        return historyService.getHistory();
    }


    @KafkaListener(topics = "words.processed", groupId = "group_id")
    public void consumeMessage(String message) throws JsonProcessingException {
        History history = new ObjectMapper().readValue(message, History.class);
        log.info(history.toString());
        historyService.addHistory(history);
    }
}
