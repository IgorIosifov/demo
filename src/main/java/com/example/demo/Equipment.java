package com.example.demo;


import org.springframework.stereotype.Component;

@Component("Equipment")
public class Equipment {

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    private String trainingId;

    private String distance = "0";

    private String actualDistance = "0";

    private String duration = "0";

    public Equipment() {
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(String actualDistance) {
        this.actualDistance = actualDistance;
    }

    public String getTrainingId() {
        return trainingId;
    }

}
