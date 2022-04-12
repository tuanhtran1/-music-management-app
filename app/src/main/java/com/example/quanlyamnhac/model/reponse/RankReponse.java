package com.example.quanlyamnhac.model.reponse;

public class RankReponse {
    private String ten;
    private String casi;
    private int img;
    private String nhacsi;
    private int hang;

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }

    public String getNhacsi() {
        return nhacsi;
    }

    public void setNhacsi(String nhacsi) {
        this.nhacsi = nhacsi;
    }

    public String getName() {
        return ten;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.ten = name;
    }

    public String getDecription() {
        return casi;
    }

    public void setDecription(String decription) {
        this.casi = decription;
    }
}
