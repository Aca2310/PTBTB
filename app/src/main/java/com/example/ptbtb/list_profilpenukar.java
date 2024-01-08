package com.example.ptbtb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class list_profilpenukar {
    private String user_id;
    private String username;
    private String nama;
    private String dataTitle;
    private String dataDetail;
    private String dataLocation;
    private String dataBarter;
    private String dataImage;

    public list_profilpenukar() {

    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDetail() {
        return dataDetail;
    }
    public String getDataLocation() { return dataLocation;}

    public String getDataBarter() {
        return dataBarter;
    }

    public String getDataImage() {
        return dataImage;
    }

    public list_profilpenukar(String user_id, String username, String nama, String dataTitle, String dataDetail, String dataBarter, String dataImage) {
        this.user_id = user_id;
        this.username = username;
        this.nama = nama;
        this.dataTitle = dataTitle;
        this.dataDetail = dataDetail;
        this.dataBarter = dataBarter;
        this.dataImage = dataImage;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }
}