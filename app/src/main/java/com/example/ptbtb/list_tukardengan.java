package com.example.ptbtb;

public class list_tukardengan {
    private String user_id;
    private String dataTitle;
    private String dataBarter;
    private String dataImage;


    public  list_tukardengan  (){

    }

    public list_tukardengan(String dataTitle, String dataBarter, String dataImage) {
        this.user_id = user_id;
        this.dataTitle = dataTitle;
        this.dataBarter = dataBarter;
        this.dataImage = dataImage;

    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataBarter() {
        return dataBarter;
    }

    public void setDataBarter(String dataBarter) {
        this.dataBarter = dataBarter;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }
}