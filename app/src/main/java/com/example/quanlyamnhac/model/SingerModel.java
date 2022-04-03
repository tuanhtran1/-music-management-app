package com.example.quanlyamnhac.model;

import java.io.Serializable;

public class SingerModel implements Serializable {
    String id;
    String name;
    String linkImg;

    public SingerModel() {

    }

    public SingerModel(String id, String name, String linkImg) {
        this.id = id;
        this.name = name;
        this.linkImg = linkImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
