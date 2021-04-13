package com.betvictor.repository;

import com.betvictor.dto.History;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {

}
