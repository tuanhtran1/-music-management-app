package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class MusicianReponse implements Serializable {
    private String songName;
    private String yearOfCreation;
    private String singerName;
    public MusicianReponse() {
    }

    public MusicianReponse(String songName, String singerName, String yearOfCreation) {
        this.songName = songName;
        this.singerName = singerName;
        this.yearOfCreation = yearOfCreation;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
