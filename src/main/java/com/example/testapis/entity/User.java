package com.example.testapis.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {
    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private String password;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    private String headpic;
}