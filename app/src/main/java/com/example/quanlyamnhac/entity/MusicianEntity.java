package com.example.quanlyamnhac.entity;

import java.io.Serializable;

public class MusicianEntity implements Serializable {
    Integer id;
    String name;
    String linkImg;

    public MusicianEntity() {

    }

    public MusicianEntity(Integer id, String name, String linkImg) {
        this.id = id;
        this.name = name;
        this.linkImg = linkImg;
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

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
