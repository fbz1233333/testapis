package com.example.testapis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class User implements Serializable {
    private String id;

    private String name;

    private String password;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}
