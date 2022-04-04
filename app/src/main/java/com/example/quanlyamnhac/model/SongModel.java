package com.example.quanlyamnhac.model;

import java.io.Serializable;

public class SongModel implements Serializable {
    private String musicName;
    private String performanceDay;
    private String place;

    public SongModel() {
    }

    public SongModel(String musicName, String performanceDay, String place) {
        this.musicName = musicName;
        this.performanceDay = performanceDay;
        this.place = place;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setSingerName(String musicName) {
        this.musicName = musicName;
    }

    public String getPerformanceDay() {
        return performanceDay;
    }

    public void setPerformanceDay(String performanceDay) {
        this.performanceDay = performanceDay;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
