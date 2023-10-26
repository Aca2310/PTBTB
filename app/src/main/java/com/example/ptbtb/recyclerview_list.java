package com.example.ptbtb;

public class recyclerview_list {
    private Integer image;
    private  String text,detail,lokasi,barter;

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

    public recyclerview_list(Integer image, String text, String detail, String lokasi, String barter) {
        this.image = image;
        this.text = text;
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
