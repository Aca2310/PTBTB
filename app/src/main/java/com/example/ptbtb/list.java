package com.example.ptbtb;

public class list {

    private Integer image;
    private  String text,text2;

    public list(Integer image, String text, String text2) {
        this.image = image;
        this.text = text;
        this.text2 = text2;
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

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
