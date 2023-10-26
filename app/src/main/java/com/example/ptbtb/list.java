package com.example.ptbtb;

public class list {

    private Integer image;
    private  String judul,subjudl;

    public list(Integer image, String judul, String subjudul) {
        this.image = image;
        this.judul = judul;
        this.subjudl = subjudul;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSubjudl() {
        return subjudl;
    }

    public void setSubjudl(String subjudl) {
        this.subjudl = subjudl;
    }
}
