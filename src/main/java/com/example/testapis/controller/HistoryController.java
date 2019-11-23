package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.History;
import com.example.testapis.info.I01;
import com.example.testapis.mapper.HistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HistoryController {

    static Logger logger = LoggerFactory.getLogger(HistoryController.class);

    @Autowired
    HistoryMapper historyMapper;

    @GetMapping("history/getHistoryByUid/{id}")
    @UserLoginToken
    public Object getByUid(@PathVariable String id){
        logger.info("history:{}",id);
        HashMap<String ,Object> map=new HashMap<>();
        map.put("historys",historyMapper.findAllByUserid(id));
        return map;
    }

    @PostMapping("history")
    @UserLoginToken
    public void iii(@RequestBody History history){
        logger.info("历史信息为:{}",history);
        history.setId(UUID.randomUUID().toString());
        historyMapper.insert(history);

    }

    @DeleteMapping("history/{id}")
    @UserLoginToken
    public void iii(@PathVariable String id){
        historyMapper.deleteByPrimaryKey(id);
    }
}
