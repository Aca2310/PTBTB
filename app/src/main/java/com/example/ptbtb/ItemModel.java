package com.example.ptbtb;

public class ItemModel {
    String name;
    int poster;

    public ItemModel(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public int getPoster() {
        return poster;
    }
}
