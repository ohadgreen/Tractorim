package com.acme.tracktorim.repositories;

import com.acme.tracktorim.model.WorkActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkActivityRepository extends MongoRepository<WorkActivity, Integer> {
}
