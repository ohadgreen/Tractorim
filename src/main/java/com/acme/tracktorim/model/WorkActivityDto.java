package com.acme.tracktorim.model;

import java.time.LocalDateTime;

public class WorkActivityDto {
    private int id;
    private Tractor tractor;
    private Plot plot;
    private Activity activity;
    private Boolean isOngoing;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float areaDone;
    private float fuelConsumption;
    private Float averageOutputPerHour;
    private Float averageFuelConsumption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tractor getTractor() {
        return tractor;
    }

    public void setTractor(Tractor tractor) {
        this.tractor = tractor;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public Float getAverageOutputPerHour() {
        return averageOutputPerHour;
    }

    public void setAverageOutputPerHour(Float averageOutputPerHour) {
        this.averageOutputPerHour = averageOutputPerHour;
    }

    public Float getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(Float averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }
}
