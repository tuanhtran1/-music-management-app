package com.example.quanlyamnhac.entity;

import java.util.Date;

public class PerformanceInfoEntity {
    private Integer id;
    private Integer singerId;
    private Integer songId;
    private Date performanceDay;
    private String place;

    public PerformanceInfoEntity(){
    }

    public PerformanceInfoEntity(Integer id, Integer singerId, Integer songId, Date performanceDay, String place) {
        this.id = id;
        this.singerId = singerId;
        this.songId = songId;
        this.performanceDay = performanceDay;
        this.place = place;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Date getPerformanceDay() {
        return performanceDay;
    }

    public void setPerformanceDay(Date performanceDay) {
        this.performanceDay = performanceDay;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
