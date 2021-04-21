package com.betvictor.repository.service;

import com.betvictor.repository.dto.History;
import com.betvictor.repository.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void addHistory(History history) {
        historyRepository.save(history);
    }

    @Override
    public List<History> getHistory() {
        List<History> histories = historyRepository.findAll();
        Collections.reverse(histories);
        return histories.stream()
                .limit(10)
                .collect(Collectors.toList());
    }
}
