package com.acme.tracktorim.repositories;

import com.acme.tracktorim.model.Plot;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlotRepository extends MongoRepository<Plot, Integer> {
}
