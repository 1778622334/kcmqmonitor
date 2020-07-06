package com.gzhz.kcmqmonitor.vo;

public class KcSearchResultVo {
    private String id;

    private String title;

    private String info;

    private String[] receiverusers;

    private String[] receiveruserids;

    private String[] files;

    private String publishtime;

    private String publishuser;

    private String publishuserid;
    /**
     * 头像地址
     */
    private String headImageUrl;

    private String[] favorites;

    private Byte type;   //  1我发出的 2我接收的 3我关注的 4已删除的  8草稿箱中的

    private String subtitle;  // 副标题  优先级 设置高亮参数，优先级:附件>发起人>接收人>附言（info）>发起人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String[] getReceiverusers() {
        return receiverusers;
    }

    public void setReceiverusers(String[] receiverusers) {
        this.receiverusers = receiverusers;
    }

    public String[] getReceiveruserids() {
        return receiveruserids;
    }

    public void setReceiveruserids(String[] receiveruserids) {
        this.receiveruserids = receiveruserids;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getPublishuser() {
        return publishuser;
    }

    public void setPublishuser(String publishuser) {
        this.publishuser = publishuser;
    }

    public String getPublishuserid() {
        return publishuserid;
    }

    public void setPublishuserid(String publishuserid) {
        this.publishuserid = publishuserid;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String[] getFavorites() {
        return favorites;
    }

    public void setFavorites(String[] favorites) {
        this.favorites = favorites;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
