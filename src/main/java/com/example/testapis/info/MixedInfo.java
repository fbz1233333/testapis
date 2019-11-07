package com.example.testapis.info;

import lombok.Data;

import java.util.List;

@Data
public class MixedInfo {
    List<String> mixedStr;

    Integer limit;
}
