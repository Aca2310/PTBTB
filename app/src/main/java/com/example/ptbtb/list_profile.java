package com.example.ptbtb;

public class list_profile {
    private String user_id;
    private String dataTitle;
    private String dataDetail;
    private String dataLocation;
    private String dataBarter;
    private String dataImage;
    private String username;

    private String key ;

    public  list_profile  (){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public list_profile(String key) {
        this.key = key;
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

    public list_profile (String user_id, String username, String dataTitle, String dataDetail,  String dataLocation, String dataBarter, String dataImage) {
        this.user_id = user_id;
        this.dataTitle = dataTitle;
        this.dataDetail = dataDetail;
        this.dataLocation = dataLocation;
        this.dataBarter = dataBarter;
        this.dataImage = dataImage;
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }
}