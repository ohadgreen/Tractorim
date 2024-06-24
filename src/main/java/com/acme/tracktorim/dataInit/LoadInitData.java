package com.acme.tracktorim.dataInit;

import com.acme.tracktorim.model.Activity;
import com.acme.tracktorim.model.Crop;
import com.acme.tracktorim.model.Plot;
import com.acme.tracktorim.model.Tractor;
import com.acme.tracktorim.model.WorkActivity;
import com.acme.tracktorim.repositories.ActivityRepository;
import com.acme.tracktorim.repositories.PlotRepository;
import com.acme.tracktorim.repositories.WorkActivityRepository;
import com.acme.tracktorim.services.SequenceGeneratorService;
import com.acme.tracktorim.services.TractorService;
import com.acme.tracktorim.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
public class LoadInitData implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(LoadInitData.class);
    @Autowired
    private TractorService tractorService;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private WorkActivityRepository workActivityRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Override
    public void run(String... args) throws Exception {

        initDimensions();

        List<WorkActivity> workActivityList = workActivityRepository.findAll();
        if (!workActivityList.isEmpty()) {
            logger.info("Work activities data already exists");
            return;
        }

        logger.info("Loading initial work activities data");
        List<Activity> activityList = activityRepository.findAll();
        List<Plot> plotList = plotRepository.findAll();
        List<Tractor> tractorList = tractorService.getAllTractors();
        LocalDateTime activitiesBaseDate = LocalDateTime.now().minusDays(20).withHour(7).withMinute(0).withSecond(0);

        for (int i = 0; i < 20; i++) {
            for (Tractor tractor : tractorList) {
                int plotIndex = Utils.getRandomNum(0, plotList.size());
                int activityIndex = Utils.getRandomNum(0, activityList.size());
                LocalDateTime startDate = activitiesBaseDate.plusDays(i).plusHours(Utils.getRandomNum(0, 3));
                LocalDateTime endDate = startDate.plusMinutes(Utils.getRandomNum(0, 360));
                WorkActivity workActivity = new WorkActivity(sequenceGeneratorService.generateSequence(WorkActivity.SEQUENCE_NAME),
                        tractor.getId(),
                        plotList.get(plotIndex).getId(),
                        activityList.get(activityIndex).getId(),
                        false,
                        true,
                        startDate,
                        endDate,
                        plotList.get(plotIndex).getAreaHectares(),
                        Utils.getRandomNum(80, 120));
                workActivityRepository.save(workActivity);
            }
        }

        // init ongoing activities
        for (Tractor tractor : tractorList) {
            int plotIndex = Utils.getRandomNum(0, plotList.size());
            int activityIndex = Utils.getRandomNum(0, activityList.size());
            LocalDateTime startDate = LocalDateTime.now().withHour(7).withMinute(0).withSecond(0);
            WorkActivity workActivity = new WorkActivity(sequenceGeneratorService.generateSequence(WorkActivity.SEQUENCE_NAME),
                    tractor.getId(),
                    plotList.get(plotIndex).getId(),
                    activityList.get(activityIndex).getId(),
                    true,
                    false,
                    startDate,
                    null,
                    plotList.get(plotIndex).getAreaHectares(),
                    Utils.getRandomNum(10, 80));
            workActivityRepository.save(workActivity);

        }

    }

    private void initDimensions() {
        List<Tractor> allTractors = tractorService.getAllTractors();
        if (!allTractors.isEmpty()) {
            logger.info("Dimensions data already exists");
            return;
        }

        logger.info("Loading initial dimensions data");
        Tractor t1 = new Tractor(sequenceGeneratorService.generateSequence(Tractor.SEQUENCE_NAME), "John Deere", "S680", 2019, 400);
        tractorService.saveTractor(t1);
        Tractor t2 = new Tractor(sequenceGeneratorService.generateSequence(Tractor.SEQUENCE_NAME), "John Deere", "S780", 2020, 450);
        tractorService.saveTractor(t2);
        Tractor t3 = new Tractor(sequenceGeneratorService.generateSequence(Tractor.SEQUENCE_NAME), "Case International", "MX350", 2018, 350);
        tractorService.saveTractor(t3);
        Tractor t4 = new Tractor(sequenceGeneratorService.generateSequence(Tractor.SEQUENCE_NAME), "Case International", "MX370", 2019, 370);
        tractorService.saveTractor(t4);
        List<Tractor> tractorList = tractorService.getAllTractors();
        logger.info("Tractors loaded: " + tractorList.size());

        Plot p1 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-1", 100, Crop.WHEAT);
        plotRepository.save(p1);
        Plot p2 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-2", 128, Crop.WHEAT);
        plotRepository.save(p2);
        Plot p3 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-3", 225, Crop.WHEAT);
        plotRepository.save(p3);
        Plot p4 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-4", 355, Crop.WHEAT);
        plotRepository.save(p4);
        Plot p5 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-5", 280, Crop.CORN);
        plotRepository.save(p5);
        Plot p6 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-6", 308, Crop.CORN);
        plotRepository.save(p6);
        Plot p7 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-7", 190, Crop.CORN);
        plotRepository.save(p7);
        Plot p8 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-8", 232, Crop.CORN);
        plotRepository.save(p8);
        Plot p9 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-9", 160, Crop.POTATO);
        plotRepository.save(p9);
        Plot p10 = new Plot(sequenceGeneratorService.generateSequence(Plot.SEQUENCE_NAME), "P-10", 149, Crop.POTATO);
        plotRepository.save(p10);

        Activity a1 = new Activity(sequenceGeneratorService.generateSequence(Activity.SEQUENCE_NAME), "Plowing", "JD Plow 2050");
        activityRepository.save(a1);
        Activity a2 = new Activity(sequenceGeneratorService.generateSequence(Activity.SEQUENCE_NAME), "Sowing", "JD Sower 1012");
        activityRepository.save(a2);
        Activity a3 = new Activity(sequenceGeneratorService.generateSequence(Activity.SEQUENCE_NAME), "Fertilizing", "Hardy 310");
        activityRepository.save(a3);
        Activity a4 = new Activity(sequenceGeneratorService.generateSequence(Activity.SEQUENCE_NAME), "Cultivation", "MF Cultivator 2020");
        activityRepository.save(a4);
        Activity setUpActivity = new Activity(sequenceGeneratorService.generateSequence(Activity.SEQUENCE_NAME), "Setup", null);
        activityRepository.save(setUpActivity);
    }

}
