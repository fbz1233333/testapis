package com.example.testapis.entity;

import lombok.Data;

@Data
public class Comment {
    private String id;

    private String userid;

    private String username;

    private String userpic;

    private String comment;

    private Integer repeattoid;

    private String mediaid;
}