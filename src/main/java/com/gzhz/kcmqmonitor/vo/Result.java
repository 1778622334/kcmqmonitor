package com.gzhz.kcmqmonitor.vo;

import com.gzhz.kcmqmonitor.entity.KcSearchNewModel;

import java.util.List;

public class Result {


    /**
     * jobId : null
     * clientId : null
     * collection : gz_kc
     * resourceId : ["8cf65644-86e8-4694-b69f-4943fc0504e8"]
     * full : false
     * scoreLimit : 0
     * includeOtherTag : false
     * otherTagName : ??
     * thread : 15
     * batchSize : 100
     * solrThread : 0
     * persistTag : true
     * maxTagNum : 3
     * payload : [{"favorites":["32132132","321312312","23213213"],"files":["***5?????","***7?????","***8?????"],"id":"123213213132","info":"????? ?????","publishtime":"2020-06-11T12:20:36Z","publishuser":"???","publishuserid":"2557623232","receiveruserids":["323232122","231312321321","3213213213"],"receiverusers":["??","??","???"],"title":"??????","type":1}]
     * fieldMapping : null
     * attachmentField : null
     */


    private Object jobId;

    private Object clientId;

    private String collection;
    private boolean full;
    private String scoreLimit;
    private boolean includeOtherTag;
    private String otherTagName;
    private int thread;
    private int batchSize;
    private int solrThread;
    private boolean persistTag;
    private int maxTagNum;
    private Object fieldMapping;
    private Object attachmentField;
    private List<String> resourceId;
    private List<KcSearchNewModel> payload;

    public List<KcSearchNewModel> getPayload() {
        return payload;
    }

    public void setPayload(List<KcSearchNewModel> payload) {
        this.payload = payload;
    }

    public Object getJobId() {
        return jobId;
    }

    public void setJobId(Object jobId) {
        this.jobId = jobId;
    }

    public Object getClientId() {
        return clientId;
    }

    public void setClientId(Object clientId) {
        this.clientId = clientId;
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

    public String getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(String scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public boolean isIncludeOtherTag() {
        return includeOtherTag;
    }

    public void setIncludeOtherTag(boolean includeOtherTag) {
        this.includeOtherTag = includeOtherTag;
    }

    public String getOtherTagName() {
        return otherTagName;
    }

    public void setOtherTagName(String otherTagName) {
        this.otherTagName = otherTagName;
    }

    public int getThread() {
        return thread;
    }

    public void setThread(int thread) {
        this.thread = thread;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getSolrThread() {
        return solrThread;
    }

    public void setSolrThread(int solrThread) {
        this.solrThread = solrThread;
    }

    public boolean isPersistTag() {
        return persistTag;
    }

    public void setPersistTag(boolean persistTag) {
        this.persistTag = persistTag;
    }

    public int getMaxTagNum() {
        return maxTagNum;
    }

    public void setMaxTagNum(int maxTagNum) {
        this.maxTagNum = maxTagNum;
    }

    public Object getFieldMapping() {
        return fieldMapping;
    }

    public void setFieldMapping(Object fieldMapping) {
        this.fieldMapping = fieldMapping;
    }

    public Object getAttachmentField() {
        return attachmentField;
    }

    public void setAttachmentField(Object attachmentField) {
        this.attachmentField = attachmentField;
    }

    public List<String> getResourceId() {
        return resourceId;
    }

    public void setResourceId(List<String> resourceId) {
        this.resourceId = resourceId;
    }




}
