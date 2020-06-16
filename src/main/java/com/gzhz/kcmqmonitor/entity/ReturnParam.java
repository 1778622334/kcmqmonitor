package com.gzhz.kcmqmonitor.entity;

public class ReturnParam {
    private String[] field;
    private int rows;
    private int start;

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public ReturnParam(String[] field, int rows, int start) {
        this.field = field;
        this.rows = rows;
        this.start = start;
    }

    public ReturnParam() {
    }
}
