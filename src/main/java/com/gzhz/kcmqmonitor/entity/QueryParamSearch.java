package com.gzhz.kcmqmonitor.entity;

public class QueryParamSearch {
    private String query;
    private String[] filterQuery;
    private String[] sortField;

//    "queryParam": {
//        "query": "测试",
//                "filterQuery": [
//        "files:* OR title:* OR receiverusers:* OR publishuser:*",
//                "type:1",
//                "publishtime:[1999-06-01T00:00:00Z TO 2000-01-01T05:00:00Z]"
//          ]
//    },

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String[] getFilterQuery() {
        return filterQuery;
    }

    public void setFilterQuery(String[] filterQuery) {
        this.filterQuery = filterQuery;
    }

    public String[] getSortField() {
        return sortField;
    }

    public void setSortField(String[] sortField) {
        this.sortField = sortField;
    }
}
