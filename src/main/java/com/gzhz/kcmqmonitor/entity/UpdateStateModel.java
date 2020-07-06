package com.gzhz.kcmqmonitor.entity;

public class UpdateStateModel {
    private String action;
    private String collection;
    private KcState [] data;

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

    public KcState[] getData() {
        return data;
    }

    public void setData(KcState[] data) {
        this.data = data;
    }
}
