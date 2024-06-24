package com.acme.tracktorim.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "activities")
public class Activity {
    @Transient
    public static final String SEQUENCE_NAME = "ACTIVITIES";
    @Id
    private int id;
    private String name;
    private String equipment;

    public Activity(int id, String name, String equipment) {
        this.id = id;
        this.name = name;
        this.equipment = equipment;
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

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
