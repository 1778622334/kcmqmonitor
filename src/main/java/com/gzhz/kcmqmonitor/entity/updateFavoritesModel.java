package com.gzhz.kcmqmonitor.entity;

public class updateFavoritesModel {
    private String action;
    private String collection;

    private Favorites[]  data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Favorites[] getData() {
        return data;
    }

    public void setData(Favorites[] data) {
        this.data = data;
    }
}
