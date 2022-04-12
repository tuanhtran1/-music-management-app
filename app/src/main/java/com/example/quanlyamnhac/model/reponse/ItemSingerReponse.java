package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class ItemSingerReponse implements Serializable {
    private String nameSinger;
    private String imageSinger;

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getImageSinger() {
        return imageSinger;
    }

    public void setImageSinger(String imageSinger) {
        this.imageSinger = imageSinger;
    }

    public ItemSingerReponse(String nameSinger, String imageSinger) {
        this.nameSinger = nameSinger;
        this.imageSinger = imageSinger;
    }

    public ItemSingerReponse() {
    }
}
