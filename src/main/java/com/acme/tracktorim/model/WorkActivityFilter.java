package com.acme.tracktorim.model;

import java.time.LocalDateTime;

public class WorkActivityFilter {
    private Integer tractorId;
    private Integer plotId;
    private Integer activityId;
    private Boolean isFinished;
    private Boolean isOngoing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Integer getTractorId() {
        return tractorId;
    }

    public void setTractorId(Integer tractorId) {
        this.tractorId = tractorId;
    }

    public Integer getPlotId() {
        return plotId;
    }

    public void setPlotId(Integer plotId) {
        this.plotId = plotId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getOngoing() {
        return isOngoing;
    }

    public void setOngoing(Boolean ongoing) {
        isOngoing = ongoing;
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
}
