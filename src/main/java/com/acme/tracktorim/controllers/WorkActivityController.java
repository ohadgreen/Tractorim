package com.acme.tracktorim.controllers;

import com.acme.tracktorim.model.CumulateNum;
import com.acme.tracktorim.model.WorkActivityDto;
import com.acme.tracktorim.model.WorkActivityFilter;
import com.acme.tracktorim.services.WorkActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/work-activities")
public class WorkActivityController {

    Logger logger = LoggerFactory.getLogger(WorkActivityController.class);
    @Autowired
    private WorkActivityService workActivityService;

    @PostMapping("/byFilterPost")
    public List<WorkActivityDto> getWorkActivityDtoListWithPost(@RequestBody WorkActivityFilter workActivityFilter) {
        logger.info("Getting work activities by filter: " + workActivityFilter);
        return workActivityService.getWorkActivitiesByFilter(workActivityFilter);
    }

    @GetMapping("/byFilterGet")
    public List<WorkActivityDto> getWorkActivityDtoList(WorkActivityFilter workActivityFilter) {
        logger.info("Getting work activities by filter: " + workActivityFilter);
        return workActivityService.getWorkActivitiesByFilter(workActivityFilter);
    }

    @GetMapping("/getOngoing/{ongoing}")
    public List<WorkActivityDto> getOnGoingWorkActivityDtoList(@PathVariable Boolean ongoing) {
        logger.info("Getting ongoing activities: " + ongoing);
        WorkActivityFilter workActivityFilter = new WorkActivityFilter();
        workActivityFilter.setOngoing(ongoing);
        return workActivityService.getWorkActivitiesByFilter(workActivityFilter);
    }

    @GetMapping("/getCumulateTest")
    public CumulateNum getCumulateTest() {
        return workActivityService.getCumulateTest();
    }
}
