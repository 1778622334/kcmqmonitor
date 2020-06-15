package com.gzhz.kcmqmonitor.entity;

public class InsertModel {
    private int batchSize = 100;        //批量处理的数据，默认100即可
    private String collection = "gz_kc";        //数据集名称
    private boolean full = false;        //默认false
    private String otherTagName = "其他";       //未分类的标注为“其他”，默认使用其他即可
    private KcSearchNewModel[] payload ;        //增量数据
    private String[] resourceId = new String[] {"8cf65644-86e8-4694-b69f-4943fc0504e8"} ; //资源id，默认这个就可以

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public String getOtherTagName() {
        return otherTagName;
    }

    public void setOtherTagName(String otherTagName) {
        this.otherTagName = otherTagName;
    }

    public KcSearchNewModel[] getPayload() {
        return payload;
    }

    public void setPayload(KcSearchNewModel[] payload) {
        this.payload = payload;
    }

    public String[] getResourceId() {
        return resourceId;
    }

    public void setResourceId(String[] resourceId) {
        this.resourceId = resourceId;
    }
}
