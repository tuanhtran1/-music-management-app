package com.example.quanlyamnhac.entity;

public class SongEntity {
    private Long id;
    private String name;
    private String yearCreation;
    private Long musicianId;

    public SongEntity() {
    }

    public SongEntity(Long id, String name, String yearCreation, Long musicianId) {
        this.id = id;
        this.name = name;
        this.yearCreation = yearCreation;
        this.musicianId = musicianId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(Long musicianId) {
        this.musicianId = musicianId;
    }
}
