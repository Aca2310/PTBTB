package com.example.ptbtb;

public class listArtikel {
    private String judul, desc, imageArtikel;

    String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public listArtikel(String judul, String desc, String imageArtikel) {
        this.judul = judul;
        this.desc = desc;
        this.imageArtikel = imageArtikel;
    }

    public  listArtikel(){
    }
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageArtikel() {
        return imageArtikel;
    }

    public void setImageArtikel(String imageArtikel) {
        this.imageArtikel = imageArtikel;
    }

}
