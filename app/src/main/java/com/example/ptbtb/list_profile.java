package com.example.ptbtb;

public class list_profile {
    private Integer image;
    private  String text,judul,detail,lokasi,barter;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getBarter() {
        return barter;
    }

    public void setBarter(String barter) {
        this.barter = barter;
    }

    public list_profile(Integer image, String text, String judul, String detail, String lokasi, String barter) {
        this.image = image;
        this.text = text;
        this.judul = judul;
        this.detail = detail;
        this.lokasi = lokasi;
        this.barter = barter;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}