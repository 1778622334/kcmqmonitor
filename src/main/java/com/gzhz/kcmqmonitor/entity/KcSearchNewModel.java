package com.gzhz.kcmqmonitor.entity;

import java.util.Arrays;

public class KcSearchNewModel {

    private String id;


    private String title;


    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.receiverUsers
     *  接收人名字 多个以,隔开
     * @mbggenerated
     */
    private String[] receiverusers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.receiverUserIds
     *接收人id 多个以,隔开
     * @mbggenerated
     */
    private String[] receiveruserids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.files
     *附件名字 多个以,隔开
     * @mbggenerated
     */
    private String[] files;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.publishTime
     *
     * @mbggenerated
     */
    private String publishtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.PublishUser
     *
     * @mbggenerated
     */
    private String publishuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.PublishUserId
     *
     * @mbggenerated
     */
    private String publishuserid;
    /**
     * 头像地址
     */
    private String headImageUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.favorites
     *关注人 id  列表 多个以,隔开
     * @mbggenerated
     */
    private String[] favorites;

    private Byte type;   // 1 正常发出的   4已删除的   8草稿箱中的

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

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    @Override
    public String toString() {
        return "KcSearchNewModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", receiverusers=" + Arrays.toString(receiverusers) +
                ", receiveruserids=" + Arrays.toString(receiveruserids) +
                ", files=" + Arrays.toString(files) +
                ", publishtime='" + publishtime + '\'' +
                ", publishuser='" + publishuser + '\'' +
                ", publishuserid='" + publishuserid + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", favorites=" + Arrays.toString(favorites) +
                ", type=" + type +
                '}';
    }
}
