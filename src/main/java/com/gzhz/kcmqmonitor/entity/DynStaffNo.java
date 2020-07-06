package com.gzhz.kcmqmonitor.entity;

public class DynStaffNo {
//    private Long staffid;

    private Long staffno;

//    private String guid;

    private String username;

    private String staffname;

    private String headImageUrl;

    public DynStaffNo() {
    }

    public DynStaffNo(Long staffno, String username, String staffname, String headImageUrl) {
        this.staffno = staffno;
        this.username = username;
        this.staffname = staffname;
        this.headImageUrl = headImageUrl;
    }

    public DynStaffNo(Long staffno, String username, String staffname) {
        this.staffno = staffno;
        this.username = username;
        this.staffname = staffname;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public Long getStaffno() {
        return staffno;
    }

    public void setStaffno(Long staffno) {
        this.staffno = staffno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }
}
