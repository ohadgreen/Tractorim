package com.acme.tracktorim.services;

import com.acme.tracktorim.model.Tractor;
import com.acme.tracktorim.repositories.TractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TractorService {

    @Autowired
    private TractorRepository tractorRepository;
    @Autowired
    private MongoOperations mongoOperations;

    public Tractor getTractorById(Integer id) {
        return tractorRepository.findById(id).orElse(null);
    }

    public Tractor saveTractor(Tractor tractor) {
        return tractorRepository.save(tractor);
    }

    public List<Tractor> getAllTractors() {
        return tractorRepository.findAll();
    }
}
