package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class SingerReponse implements Serializable {
    private String songName;
    private String musicianName;
    private String yearOfCreation;

    public SingerReponse() {
    }

    public SingerReponse(String songName, String musicianName, String yearOfCreation) {
        this.songName = songName;
        this.musicianName = musicianName;
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

    public String getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(String yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
