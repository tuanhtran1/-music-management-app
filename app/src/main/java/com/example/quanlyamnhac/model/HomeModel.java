package com.example.quanlyamnhac.model;

public class HomeModel {
    private  String stt;
    private String music;
    private String musical;
    private String yearOfCreation;

    public HomeModel() {
    }

    public HomeModel(String stt, String music, String musical, String yearOfCreation) {
        this.stt = stt;
        this.music = music;
        this.musical = musical;
        this.yearOfCreation = yearOfCreation;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getMusical() {
        return musical;
    }

    public void setMusical(String musical) {
        this.musical = musical;
    }

    public String getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(String yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
