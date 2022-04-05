package com.example.quanlyamnhac.model.reponse;

import java.io.Serializable;

public class ItemMusicianReponse implements Serializable {
    private String nameMusician;
    private String imageMusician;

    public String getNameMusician() {
        return nameMusician;
    }

    public void setNameMusician(String nameMusician) {
        this.nameMusician = nameMusician;
    }

    public String getImageMusician() {
        return imageMusician;
    }

    public void setImageMusician(String imageMusician) {
        this.imageMusician = imageMusician;
    }

    public ItemMusicianReponse(String nameMusician, String imageMusician) {
        this.nameMusician = nameMusician;
        this.imageMusician = imageMusician;
    }

    public ItemMusicianReponse(){}
}
