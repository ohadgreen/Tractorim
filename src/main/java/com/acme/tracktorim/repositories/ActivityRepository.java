package com.acme.tracktorim.repositories;

import com.acme.tracktorim.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, Integer> {
    Activity findActivityByName(String name);
}
