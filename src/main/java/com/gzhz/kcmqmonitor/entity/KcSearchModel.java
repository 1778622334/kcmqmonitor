package com.gzhz.kcmqmonitor.entity;

import java.util.Date;

public class KcSearchModel {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.info
     *
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.receiverUsers
     *  接收人名字 多个以,隔开
     * @mbggenerated
     */
    private String receiverusers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.receiverUserIds
     *接收人id 多个以,隔开
     * @mbggenerated
     */
    private String receiveruserids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.files
     *附件名字 多个以,隔开
     * @mbggenerated
     */
    private String files;

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
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kc_search_model.favorites
     *关注人 id  列表 多个以,隔开
     * @mbggenerated
     */
    private String favorites;


    private Byte type;



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

    public String getReceiverusers() {
        return receiverusers;
    }

    public void setReceiverusers(String receiverusers) {
        this.receiverusers = receiverusers;
    }

    public String getReceiveruserids() {
        return receiveruserids;
    }

    public void setReceiveruserids(String receiveruserids) {
        this.receiveruserids = receiveruserids;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishuserid() {
        return publishuserid;
    }

    public void setPublishuserid(String publishuserid) {
        this.publishuserid = publishuserid;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}