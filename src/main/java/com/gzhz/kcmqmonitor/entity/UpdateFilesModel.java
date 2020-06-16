package com.gzhz.kcmqmonitor.entity;

public class UpdateFilesModel {
    private String action;
    private String collection;
//    private String id;
    private Files [] data;

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
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public UpdateFilesModel() {
    }

    public Files[] getData() {
        return data;
    }

    public void setData(Files[] data) {
        this.data = data;
    }


}
