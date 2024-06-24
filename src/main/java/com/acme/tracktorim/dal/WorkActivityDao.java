package com.acme.tracktorim.dal;

import com.acme.tracktorim.model.WorkActivity;
import com.acme.tracktorim.model.WorkActivityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkActivityDao {
    @Autowired
    MongoTemplate mongoTemplate;

    private static final String WORK_ACTIVITY_COLLECTION_NAME = "work_activities";

    public List<WorkActivity> getWorkActivitiesByFilter(WorkActivityFilter workActivityFilter) {
        Criteria criteria = new Criteria();
        if (Boolean.TRUE.equals(workActivityFilter.getFinished())) {
            criteria.and("isFinished").is(true);
        }
        if (Boolean.TRUE.equals(workActivityFilter.getOngoing())) {
            criteria.and("isOngoing").is(true);
        }
        if (workActivityFilter.getTractorId() != null) {
            criteria.and("tractorId").is(workActivityFilter.getTractorId());
        }
        if (workActivityFilter.getActivityId() != null) {
            criteria = criteria.and("activityId").is(workActivityFilter.getActivityId());
        }

        List<WorkActivity> workActivities = mongoTemplate.find(new Query(criteria), WorkActivity.class, WORK_ACTIVITY_COLLECTION_NAME);
        return workActivities;
    }

    public void saveWorkActivity(WorkActivity workActivity) {
        mongoTemplate.save(workActivity, WORK_ACTIVITY_COLLECTION_NAME);
    }
}
