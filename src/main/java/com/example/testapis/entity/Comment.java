package com.example.testapis.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Comment {
    private String id;

    private String userid;

    private String username;

    private String userpic;

    private String comment;

    private String repeattoid;

    private String mediaid;

    private Date createTime;

    private String belongto;

    private String repeattoname;
}