package com.acme.tracktorim.controllers;

import com.acme.tracktorim.model.Tractor;
import com.acme.tracktorim.services.TractorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tractors")
public class TracktorController {

    Logger logger = LoggerFactory.getLogger(TracktorController.class);
    @Autowired
    private TractorService tractorService;

    @GetMapping("/byIdPath/{tractorId}")
    public Tractor getTractorByIdPathVar(@PathVariable("tractorId") Integer tractorId) {

        logger.info("Getting tractor by id: " + tractorId);
        return tractorService.getTractorById(tractorId);
    }

    @GetMapping("/byIdReq/{tractorId}")
    public Tractor getTractorByIdReqParam(@RequestParam("tractorId") Integer tractorId) {

        logger.info("Getting tractor by id: " + tractorId);
        return tractorService.getTractorById(tractorId);
    }

    @GetMapping("/all")
    public List<Tractor> getAllTractors() {
        return tractorService.getAllTractors();
    }

}
