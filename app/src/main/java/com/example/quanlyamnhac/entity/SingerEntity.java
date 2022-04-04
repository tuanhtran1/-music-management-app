package com.example.quanlyamnhac.entity;

import java.io.Serializable;

public class SingerEntity {
    private Long id;
    private String name;
    private String linkImg;

    public SingerEntity() {

    }

    public SingerEntity(Long id, String name, String linkImg) {
        this.id = id;
        this.name = name;
        this.linkImg = linkImg;
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

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
