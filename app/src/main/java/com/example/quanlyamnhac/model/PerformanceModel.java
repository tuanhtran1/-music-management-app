package com.example.quanlyamnhac.model;

public class PerformanceModel {
    private String stt;
    private String singerName;
    private String songName;
    private String performanceDay;
    private String place;

    public PerformanceModel(){

    }

    public PerformanceModel(String stt, String singerName, String songName, String performanceDay, String place) {
        this.stt = stt;
        this.singerName = singerName;
        this.songName = songName;
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

    public void setSingerName(String singerId) {
        this.singerName = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songId) {
        this.songName = songId;
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
