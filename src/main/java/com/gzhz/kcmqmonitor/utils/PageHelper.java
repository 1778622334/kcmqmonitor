package com.gzhz.kcmqmonitor.utils;

import java.util.List;

public class PageHelper<T> {
    private int currentPage;
    private int pageSize;
    private int totalPage;
    private int totalCount;
    private List<T> resultList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }



    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    //s设置总页数
    public void setTotalPage() {
        if (0 == this.totalCount) {
            this.totalPage = 0;
            return;
        }
        int t1 = this.totalCount % this.pageSize;
        if (t1 == 0) {
            this.totalPage = this.totalCount / this.pageSize;
        } else {
            this.totalPage = (this.totalCount / this.pageSize) + 1;
        }
    }

}
