package com.gzhz.kcmqmonitor.model;

public class SearchStaffNoListModel {
    private Long publishuser;
    private Long staffNo;
    private Integer forid;
    private Integer replyforid;
    private String sessionguid;
    private Byte isDelete;

    public SearchStaffNoListModel() {
    }

    public SearchStaffNoListModel(Long publishuser, Long staffNo, Integer forid, Integer replyforid, String sessionguid, Byte isDelete) {
        this.publishuser = publishuser;
        this.staffNo = staffNo;
        this.forid = forid;
        this.replyforid = replyforid;
        this.sessionguid = sessionguid;
        this.isDelete = isDelete;
    }

    public Long getPublishuser() {
        return publishuser;
    }

    public void setPublishuser(Long publishuser) {
        this.publishuser = publishuser;
    }

    public Long getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(Long staffNo) {
        this.staffNo = staffNo;
    }

    public Integer getForid() {
        return forid;
    }

    public void setForid(Integer forid) {
        this.forid = forid;
    }

    public Integer getReplyforid() {
        return replyforid;
    }

    public void setReplyforid(Integer replyforid) {
        this.replyforid = replyforid;
    }

    public String getSessionguid() {
        return sessionguid;
    }

    public void setSessionguid(String sessionguid) {
        this.sessionguid = sessionguid;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}
