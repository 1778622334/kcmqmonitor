package com.gzhz.kcmqmonitor.entity;

public class SearchModel {
    private String collection;
    private HighLightParam highLightParam;
    private QueryParamSearch queryParam;
    private ReturnParam returnParam;

//    {
//        "collection": "kc",
//            "highLightParam": {
//        "endTag": "</em>",
//                "field": [
//        "title"
//          ],
//        "startTag": "</em>"
//    },
//        "queryParam": {
//        "query": "测试",
//                "filterQuery": [
//        "files:* OR title:* OR receiverusers:* OR publishuser:*",
//                "type:1",
//                "publishtime:[1999-06-01T00:00:00Z TO 2000-01-01T05:00:00Z]"
//          ]
//    },
//        "returnParam": {
//        "field": [
//        "title"
//          ],
//        "rows": 10,
//                "start": 0
//    }
//    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public HighLightParam getHighLightParam() {
        return highLightParam;
    }

    public void setHighLightParam(HighLightParam highLightParam) {
        this.highLightParam = highLightParam;
    }

    public QueryParamSearch getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(QueryParamSearch queryParam) {
        this.queryParam = queryParam;
    }

    public ReturnParam getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(ReturnParam returnParam) {
        this.returnParam = returnParam;
    }
}
