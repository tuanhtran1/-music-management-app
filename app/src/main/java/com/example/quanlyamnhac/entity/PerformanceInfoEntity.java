package com.example.quanlyamnhac.entity;

public class PerformanceInfoEntity {
    private Long id;
    private Long singerId;
    private Long songId;
    private String performanceDay;
    private String place;

    public PerformanceInfoEntity(){
    }

    public PerformanceInfoEntity(Long id, Long singerId, Long songId, String performanceDay, String place) {
        this.id = id;
        this.singerId = singerId;
        this.songId = songId;
        this.performanceDay = performanceDay;
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
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
