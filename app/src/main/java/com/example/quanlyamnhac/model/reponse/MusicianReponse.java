package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class MusicianReponse implements Serializable {
    private String songName;
    private String yearOfCreation;

    public MusicianReponse() {
    }

    public MusicianReponse(String songName, String yearOfCreation) {
        this.songName = songName;
        this.yearOfCreation = yearOfCreation;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(String yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
