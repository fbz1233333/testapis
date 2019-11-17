package com.example.testapis.entity;

import java.util.Date;
import lombok.Data;

@Data
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

    private String kindInfo;

    private Integer hot;

    private String userName;

    private String state;
}