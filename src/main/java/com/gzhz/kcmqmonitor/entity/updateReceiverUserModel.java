package com.gzhz.kcmqmonitor.entity;

public class updateReceiverUserModel {
    private String action;
    private String collection;
    private ReceiverUser [] data;

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

    public ReceiverUser[] getData() {
        return data;
    }

    public void setData(ReceiverUser[] data) {
        this.data = data;
    }
}
