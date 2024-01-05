package com.example.ptbtb;

public class recyclerview_list {
    private String user_id;
    private String dataTitle;
    private String dataDetail;
    private String dataBarter;
    private String dataImage, dataLocation;

    private String username,telp;


    public  recyclerview_list (){

    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDetail() {
        return dataDetail;
    }

    public String getDataBarter() {
        return dataBarter;
    }

    public String getDataImage() {
        return dataImage;
    }

    public recyclerview_list(String user_id, String username, String dataTitle, String dataDetail, String dataBarter, String dataImage, String telp, String dataLocation) {
        this.user_id = user_id;
        this.dataTitle = dataTitle;
        this.dataDetail = dataDetail;
        this.dataBarter = dataBarter;
        this.dataImage = dataImage;
        this.username = username;
        this.telp = telp;
        this.dataLocation = dataLocation;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }
}
