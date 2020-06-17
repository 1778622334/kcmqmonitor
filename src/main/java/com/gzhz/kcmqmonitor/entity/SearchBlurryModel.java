package com.gzhz.kcmqmonitor.entity;

/**
 * 前端感知搜索传参封装类
 */
public class SearchBlurryModel {
    private String startTime;
    private String endTime;
    private String keyWord;
    private int[] findType;
    private int pageSize;
    private int currentPage;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int[] getFindType() {
        return findType;
    }

    public void setFindType(int[] findType) {
        this.findType = findType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
