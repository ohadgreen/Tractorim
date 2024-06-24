package com.acme.tracktorim.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plots")
public class Plot {
    @Transient
    public static final String SEQUENCE_NAME = "PLOTS";
    @Id
    private int id;
    private String name;
    private float areaHectares;
    private Crop crop;

    public Plot(int id, String name, float areaHectares, Crop crop) {
        this.id = id;
        this.name = name;
        this.areaHectares = areaHectares;
        this.crop = crop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getAreaHectares() {
        return areaHectares;
    }

    public void setAreaHectares(float areaHectares) {
        this.areaHectares = areaHectares;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
}
