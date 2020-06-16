package com.gzhz.kcmqmonitor.entity;

public class RemoveFile {
    private String action;
    private String collection;
    private String field;
    private String id;
    private String model;
    private Boolean multiValue;
    private String originalContent;

    /*{

        "action":"remove",

            "collection":"gz_kc",

            "field":"files",

            "id":"123213213132",

            "model":"single",

            "multiValue":true,

            "originalContent":"***5月个人台账"}*/

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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getMultiValue() {
        return multiValue;
    }

    public void setMultiValue(Boolean multiValue) {
        this.multiValue = multiValue;
    }

    public String getOriginalContent() {
        return originalContent;
    }

    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }
}
