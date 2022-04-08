package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class SongReponse  implements Serializable {
    private String singerName;
    private String performanceDay;
    private String place;

    public SongReponse(){}

    public SongReponse(String singerName, String performanceDay, String place) {
        this.singerName = singerName;
        this.performanceDay = performanceDay;
        this.place = place;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
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
