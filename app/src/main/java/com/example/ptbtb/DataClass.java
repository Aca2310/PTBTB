package com.example.ptbtb;

public class DataClass {
    private String dataTitle;
    private String dataDetail;
    private String dataBarter;
    private String dataImage;

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

    public DataClass(String dataTitle, String dataDetail, String dataBarter, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDetail = dataDetail;
        this.dataBarter = dataBarter;
        this.dataImage = dataImage;
    }
}
