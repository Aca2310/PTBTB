package com.example.ptbtb;

import android.widget.Button;

public class list {

    private String image;
    private  String judul,subjudl;
    Button detail;

    public list(String image, String judul, String subjudul) {
        this.image = image;
        this.judul = judul;
        this.subjudl = subjudul;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
