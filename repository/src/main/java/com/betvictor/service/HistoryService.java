package com.betvictor.service;

import com.betvictor.dto.History;

import java.util.List;

public interface HistoryService {

   void addHistory(History history);
   List<History> getHistory();
}
