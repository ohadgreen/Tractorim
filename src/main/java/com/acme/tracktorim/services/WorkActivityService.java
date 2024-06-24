package com.acme.tracktorim.services;

import com.acme.tracktorim.dal.WorkActivityDao;
import com.acme.tracktorim.model.Activity;
import com.acme.tracktorim.model.CumulateNum;
import com.acme.tracktorim.model.Plot;
import com.acme.tracktorim.model.Tractor;
import com.acme.tracktorim.model.WorkActivity;
import com.acme.tracktorim.model.WorkActivityDto;
import com.acme.tracktorim.model.WorkActivityFilter;
import com.acme.tracktorim.repositories.ActivityRepository;
import com.acme.tracktorim.repositories.PlotRepository;
import com.acme.tracktorim.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.acme.tracktorim.utils.Utils.getRandomNum;

@Service
public class WorkActivityService {
    Logger logger = LoggerFactory.getLogger(WorkActivityService.class);
    private int cumulateTest = 0;
    private static final String SETUP_ACTIVITY = "Setup";
    private static final String FUELING_ACTIVITY = "Fueling";
    @Autowired
    private WorkActivityDao workActivityDao;
    @Autowired
    private TractorService tractorService;
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public List<WorkActivityDto> getWorkActivitiesByFilter(WorkActivityFilter workActivityFilter) {
        List<WorkActivityDto> workActivityDtoList = new ArrayList<>();

        List<WorkActivity> workActivities = workActivityDao.getWorkActivitiesByFilter(workActivityFilter);

        if (workActivities != null) {
            Map<Integer, Tractor> tractorMap = getTractorMap();
            Map<Integer, Plot> plotMap = getPlotMap();
            Map<Integer, Activity> activityMap = getActivityMap();

            for (WorkActivity workActivity : workActivities) {
                WorkActivityDto workActivityDto = new WorkActivityDto();
                workActivityDto.setId(workActivity.getId());
                workActivityDto.setTractor(tractorMap.get(workActivity.getTractorId()));
                workActivityDto.setPlot(plotMap.get(workActivity.getPlotId()));
                workActivityDto.setActivity(activityMap.get(workActivity.getActivityId()));
                workActivityDto.setAreaDone(workActivity.getAreaDone());
                workActivityDto.setStartTime(workActivity.getStartTime());
                workActivityDto.setEndTime(workActivity.getEndTime());
                workActivityDto.setOngoing(workActivity.getOngoing());
                workActivityDto.setFuelConsumption(workActivity.getFuelConsumption());
                workActivityDto.setAverageOutputPerHour(calculateAvgOutputPerHour(workActivity, plotMap.get(workActivity.getPlotId())));
                workActivityDto.setAverageFuelConsumption(calculateAvgFuelConsumption(workActivity, plotMap.get(workActivity.getPlotId())));

                workActivityDtoList.add(workActivityDto);
            }

        }

        return workActivityDtoList;
    }

//    @Scheduled(fixedRate = 3000)
    public void cumulateUpdate() {
        logger.info("cumulate = " + cumulateTest);
        cumulateTest++;
    }

    public CumulateNum getCumulateTest() {
        return new CumulateNum(cumulateTest);
    }

    @Scheduled(fixedRate = 5000)
    public void updateOngoingWorkActivities() {
        logger.info("Updating ongoing work activities");
        WorkActivityFilter workActivityFilter = new WorkActivityFilter();
        workActivityFilter.setOngoing(true);
        List<WorkActivity> workActivities = workActivityDao.getWorkActivitiesByFilter(workActivityFilter);

        Activity setup = activityRepository.findActivityByName(SETUP_ACTIVITY);

        for (WorkActivity activity : workActivities) {
            int activityId = activity.getActivityId();

            if (activityId == setup.getId()) { // handle setup activity
                if (activity.getStartTime().isBefore(LocalDateTime.now().minusMinutes(4))) {
                    activity.setEndTime(LocalDateTime.now());
                    activity.setOngoing(false);
                    workActivityDao.saveWorkActivity(activity);

                    Map<Integer, Activity> activityMap = getActivityMap();
                    ArrayList<Integer> activityIdList = new ArrayList<>(activityMap.keySet());
                    int randomActivityId = activityIdList.get(getRandomNum(0, activityIdList.size()));

                    Map<Integer, Plot> plotMap = getPlotMap();
                    ArrayList<Integer> plotIdList = new ArrayList<>(plotMap.keySet());
                    int randomPlot = plotIdList.get(getRandomNum(0, plotIdList.size()));

                    WorkActivity newWorkActivity = new WorkActivity(sequenceGeneratorService.generateSequence(WorkActivity.SEQUENCE_NAME),
                            activity.getTractorId(),
                            randomPlot,
                            randomActivityId,
                            false,
                            true,
                            LocalDateTime.now(),
                            null,
                            plotMap.get(randomPlot).getAreaHectares(),
                            Utils.getRandomNum(80, 120));

                    workActivityDao.saveWorkActivity(newWorkActivity);

                }
                continue;
            }

            float areaDone = activity.getAreaDone();
            areaDone += (float) (activityId * 0.01);

            float fuelConsumption = activity.getFuelConsumption();
            fuelConsumption -= (float) (activityId * 0.01);

            Plot plot = plotRepository.findById(activity.getPlotId()).orElse(null);

            boolean isFinished = false;
            assert plot != null;

            if (areaDone >= plot.getAreaHectares()) {
                activity.setEndTime(LocalDateTime.now());
                activity.setOngoing(false);
                activity.setAreaDone(plot.getAreaHectares());
                isFinished = true;
                }

            if (fuelConsumption <= 3) {
                activity.setEndTime(LocalDateTime.now());
                activity.setOngoing(false);
                isFinished = true;
            }

            activity.setAreaDone(areaDone);
            activity.setFuelConsumption(fuelConsumption);
            workActivityDao.saveWorkActivity(activity);

            if (isFinished) {
                WorkActivity setupActivity = new WorkActivity(sequenceGeneratorService.generateSequence(WorkActivity.SEQUENCE_NAME),
                        activity.getTractorId(),
                        -1,
                        setup.getId(),
                        true,
                        false,
                        LocalDateTime.now(),
                        null,
                        -1f,
                        -1f);

                workActivityDao.saveWorkActivity(setupActivity);
            }
        }
    }

    private Map<Integer, Tractor> getTractorMap() {
        List<Tractor> tractorList = tractorService.getAllTractors();
        return tractorList.stream().collect(Collectors.toMap(Tractor::getId, tractor -> tractor));
    }

    private Map<Integer, Plot> getPlotMap() {
        List<Plot> plotList = plotRepository.findAll();
        return plotList.stream().collect(Collectors.toMap(Plot::getId, plot -> plot));
    }

    private Map<Integer, Activity> getActivityMap() {
        List<Activity> activityList = activityRepository.findAll();
        return activityList.stream().collect(Collectors.toMap(Activity::getId, activity -> activity));
    }

    private Float calculateAvgOutputPerHour(WorkActivity workActivity, Plot plot) {
        if (plot == null) {
            return 0f;
        }

        long activityDurationMinutes = 0;
        if (workActivity.getEndTime() == null) {
            activityDurationMinutes = Duration.between(workActivity.getStartTime(), LocalDateTime.now()).toMinutes();
        } else {
            activityDurationMinutes = Duration.between(workActivity.getStartTime(), workActivity.getEndTime()).toMinutes();
        }

        return plot.getAreaHectares() / activityDurationMinutes * 60;
    }

    private Float calculateAvgFuelConsumption(WorkActivity workActivity, Plot plot) {
        long activityDurationMinutes = 0;
        if (workActivity.getEndTime() == null) {
            activityDurationMinutes = Duration.between(workActivity.getStartTime(), LocalDateTime.now()).toMinutes();
        } else {
            activityDurationMinutes = Duration.between(workActivity.getStartTime(), workActivity.getEndTime()).toMinutes();
        }

        float fuelConsumptionPerHour = workActivity.getFuelConsumption() / activityDurationMinutes * 60;
        return fuelConsumptionPerHour;
    }

}
