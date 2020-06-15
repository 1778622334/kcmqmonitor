package com.gzhz.kcmqmonitor.model;

/**
 * 时间段类
 */
public class SearchInfoBoxModel {
    private Integer start;
    private Integer end;
    private Integer forId;
    private Integer replyforid;
    private Long staffNo;
    private Long publishUser;
    private Byte isDelete;


    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getForId() {
        return forId;
    }

    public void setForId(Integer forId) {
        this.forId = forId;
    }

    public Integer getReplyforid() {
        return replyforid;
    }

    public void setReplyforid(Integer replyforid) {
        this.replyforid = replyforid;
    }

    public Long getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Long staffNo) {
        this.staffNo = staffNo;
    }

    public Long getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(Long publishUser) {
        this.publishUser = publishUser;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public SearchInfoBoxModel() {
    }

    public SearchInfoBoxModel(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public SearchInfoBoxModel(Integer start, Integer end, Integer forId, Integer replyforid, Long staffNo, Long publishUser, Byte isDelete) {
        this.start = start;
        this.end = end;
        this.forId = forId;
        this.replyforid = replyforid;
        this.staffNo = staffNo;
        this.publishUser = publishUser;
        this.isDelete = isDelete;
    }
}
