package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class HomeReponse implements Serializable {
    private String songName;
    private String musicianName;
    private String singerName;
    private String yearOfCreation;

    public HomeReponse() {
    }

    public HomeReponse(String songName, String musicianName, String singerName, String yearOfCreation) {
        this.songName = songName;
        this.musicianName = musicianName;
        this.singerName = singerName;
        this.yearOfCreation = yearOfCreation;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getMusicianName() {
        return musicianName;
    }

    public void setMusicianName(String musicianName) {
        this.musicianName = musicianName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(String yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
