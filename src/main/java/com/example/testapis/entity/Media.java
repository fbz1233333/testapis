package com.example.testapis.entity;

import java.util.Date;

public class Media {
    private String id;

    private String title;

    private String userid;

    private Date updateTime;

    private String h1;

    private String h2;

    private String h3;

    private Integer isDel;

    private String imageinfo;

    private String downloadinfo1;

    private String downloadinfo2;

    private Date createTime;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getImageinfo() {
        return imageinfo;
    }

    public void setImageinfo(String imageinfo) {
        this.imageinfo = imageinfo;
    }

    public String getDownloadinfo1() {
        return downloadinfo1;
    }

    public void setDownloadinfo1(String downloadinfo1) {
        this.downloadinfo1 = downloadinfo1;
    }

    public String getDownloadinfo2() {
        return downloadinfo2;
    }

    public void setDownloadinfo2(String downloadinfo2) {
        this.downloadinfo2 = downloadinfo2;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}