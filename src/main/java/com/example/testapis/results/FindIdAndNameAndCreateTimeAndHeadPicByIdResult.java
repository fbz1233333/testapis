package com.example.testapis.results;

import lombok.Data;

import java.util.Date;

@Data
public class FindIdAndNameAndCreateTimeAndHeadPicByIdResult {
    private String id;
    private String name;
    private Date createTime;
    private String headPic;
}
