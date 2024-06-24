package com.acme.tracktorim.repositories;

import com.acme.tracktorim.model.Tractor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TractorRepository extends MongoRepository<Tractor, Integer> {
}
