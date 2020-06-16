package com.gzhz.kcmqmonitor.entity;


/**
 * 高亮字符集类
 * create by xujy on 2020-06-17
 */
public class HighLightParam {
    private String endTag = "</em>";
    private String startTag = "<em>";
    private String[] field;

//    "endTag": "</em>",
//            "field": [
//            "title"
//            ],
//            "startTag": "</em>"


    public String getEndTag() {
        return endTag;
    }

    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }

    public String getStartTag() {
        return startTag;
    }

    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }
}
