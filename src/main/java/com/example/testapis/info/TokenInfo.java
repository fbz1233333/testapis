package com.example.testapis.info;

import lombok.Data;

import java.util.Date;

@Data
public class TokenInfo {
    String id;

    String token;

    Date tokenTime;
}
