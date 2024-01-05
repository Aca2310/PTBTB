package com.example.ptbtb;

public class DataClass {
    private String user_id;
    private String dataTitle;
    private String dataDetail;
    private String dataBarter;
    private String dataImage;
    private String dataLocation;

    private String username, telp;
    private String key;

    public DataClass(String user_id, String username, String dataTitle, String dataDetail, String dataLocation, String dataBarter, String dataImage, String telp) {
        this.user_id = user_id;
        this.username = username;
        this.dataTitle = dataTitle;
        this.dataDetail = dataDetail;
        this.dataLocation = dataLocation;
        this.dataBarter = dataBarter;
        this.dataImage = dataImage;
        this.telp = telp;
    }

    // Getter dan setter untuk atribut baru
    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}
