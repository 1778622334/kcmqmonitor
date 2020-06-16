package com.gzhz.kcmqmonitor.entity;

public class ViewModel {
    private String collection;      //数据集名称
    private QueryParamSelectOne queryParam;       ////默认*：*

    private ReturnParam returnParam;    ////返回的字段

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public QueryParamSelectOne getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(QueryParamSelectOne queryParam) {
        this.queryParam = queryParam;
    }

    public ReturnParam getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(ReturnParam returnParam) {
        this.returnParam = returnParam;
    }
}
