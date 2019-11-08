package com.example.testapis.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.testapis.results.FindIdAndNameByPasswordAndNameResult;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class TokenUtils {
    public String getToken(FindIdAndNameByPasswordAndNameResult user) {
        String token="";
        token= JWT.create().withAudience(user.getId()).withAudience(user.getName()).withAudience(new Random().toString())
                .sign(Algorithm.HMAC256(new Date().toString()));
        return token;
    }
}
