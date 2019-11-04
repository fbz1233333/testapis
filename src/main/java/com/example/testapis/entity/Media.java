package com.example.testapis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Media implements Serializable {
    private String id;

    private String title;

    private String userid;

    private Date updateDate;

    private String h1;

    private String h2;

    private String h3;

    private Integer isDel;

    private String imageinfo;

    private String downloadinfo1;

    private String downloadinfo2;

    private static final long serialVersionUID = 1L;
}