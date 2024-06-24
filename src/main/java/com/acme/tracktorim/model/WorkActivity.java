package com.acme.tracktorim.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "work_activities")
public class WorkActivity {
    @Transient
    public static final String SEQUENCE_NAME = "WORK_ACTIVITIES";
    @Id
    private int id;
    private int tractorId;
    private int plotId;
    private int activityId;
    private Boolean isOngoing;
    private Boolean isFinished;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float areaDone;
    private float fuelConsumption;

    public WorkActivity(int id, int tractorId, int plotId, int activityId, Boolean isOngoing, Boolean isFinished, LocalDateTime startTime, LocalDateTime endTime, float areaDone, float fuelConsumption) {
        this.id = id;
        this.tractorId = tractorId;
        this.plotId = plotId;
        this.activityId = activityId;
        this.isOngoing = isOngoing;
        this.isFinished = isFinished;
        this.startTime = startTime;
        this.endTime = endTime;
        this.areaDone = areaDone;
        this.fuelConsumption = fuelConsumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTractorId() {
        return tractorId;
    }

    public void setTractorId(int tractorId) {
        this.tractorId = tractorId;
    }

    public int getPlotId() {
        return plotId;
    }

    public void setPlotId(int plotId) {
        this.plotId = plotId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public Boolean getOngoing() {
        return isOngoing;
    }

    public void setOngoing(Boolean ongoing) {
        isOngoing = ongoing;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public float getAreaDone() {
        return areaDone;
    }

    public void setAreaDone(float areaDone) {
        this.areaDone = areaDone;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
