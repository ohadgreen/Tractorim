package com.acme.tracktorim.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "tractors")
public class Tractor {
    @Transient
    public static final String SEQUENCE_NAME = "TRACTORS";
    @Id
    private int id;
    private String brand;
    private String model;
    private int year;
    private int horsePower;
    private int fuelCapacity;

    public Tractor(int id, String brand, String model, int year, int horsePower) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.horsePower = horsePower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }
}
