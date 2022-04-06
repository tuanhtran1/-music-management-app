package com.example.quanlyamnhac.entity;

public class SongEntity {
    private Integer id;
    private String name;
    private String yearCreation;
    private Long musicianId;

    public SongEntity() {
    }

    public SongEntity(Integer id, String name, String yearCreation, Long musicianId) {
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

    public Long getMusicianId() {
        return musicianId;
    }

    public void setMusicianId(Long musicianId) {
        this.musicianId = musicianId;
    }
}
