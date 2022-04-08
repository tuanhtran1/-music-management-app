package com.example.quanlyamnhac.entity;

import java.io.Serializable;

public class SongEntity implements Serializable {
    private Integer id;
    private String name;
    private String yearCreation;
    private Integer musicianId;

    public SongEntity() {
    }

    public SongEntity(Integer id, String name, String yearCreation, Integer musicianId) {
        this.id = id;
        this.name = name;
        this.yearCreation = yearCreation;
        this.musicianId = musicianId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearCreation() {
        return yearCreation;
    }

    public void setYearCreation(String yearCreation) {
        this.yearCreation = yearCreation;
    }

    public Integer getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(Integer musicianId) {
        this.musicianId = musicianId;
    }
}
