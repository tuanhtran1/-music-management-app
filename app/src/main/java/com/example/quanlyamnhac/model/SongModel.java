package com.example.quanlyamnhac.model;

public class SongModel {
    private  String stt;
    private String singerName;
    private String performanceDay;
    private String place;

    public SongModel() {
    }

    public SongModel(String stt, String singerName, String performanceDay, String place) {
        this.stt = stt;
        this.singerName = singerName;
        this.performanceDay = performanceDay;
        this.place = place;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
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
