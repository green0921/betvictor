package com.betvictor.repository.repository;

import com.betvictor.repository.dto.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {

}
