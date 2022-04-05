package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;
import java.util.Date;

public class PerformanceInfoReponse  implements Serializable {
    private String singerName;
    private String songName;
    private Date date;
    private String place;

    public PerformanceInfoReponse(){

    }

    public PerformanceInfoReponse(String singerName, String songName, Date date, String place) {
        this.singerName = singerName;
        this.songName = songName;
        this.date = date;
        this.place = place;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
