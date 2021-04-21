package com.betvictor.repository.service;

import com.betvictor.repository.dto.History;

import java.util.List;

public interface HistoryService {

   void addHistory(History history);
   List<History> getHistory();
}
