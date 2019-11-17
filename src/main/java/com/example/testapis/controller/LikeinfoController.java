package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.Collection;
import com.example.testapis.entity.Likeinfo;
import com.example.testapis.info.I01;
import com.example.testapis.mapper.CollectionMapper;
import com.example.testapis.mapper.LikeinfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LikeinfoController {

    static Logger logger = LoggerFactory.getLogger(LikeinfoController.class);

    @Autowired
    LikeinfoMapper likeinfoMapper;

    @PostMapping("like")
    @UserLoginToken
    public void insert(@RequestBody Likeinfo likeinfo){
        logger.info("请求的info:{}",likeinfo);
        likeinfo.setId(UUID.randomUUID().toString());
        likeinfoMapper.insert(likeinfo);
    }

    @PostMapping("like/get")
    @UserLoginToken
    public Object get(@RequestBody Likeinfo likeinfo){
        HashMap<String,Object> map=new HashMap<>();
        logger.info("是否点赞请求的info:{}",likeinfo);
        Likeinfo likeinfo1=likeinfoMapper.findAllByMidAndUid(likeinfo);
        //喜欢点过赞为true,不喜欢没点过为false
        logger.info("请求的结果为:{}",likeinfo1!=null);
        map.put("result",likeinfo1==null);
        return map;
    }

    @PostMapping("like/delete")
    @UserLoginToken
    public void delete(@RequestBody Likeinfo likeinfo){
        logger.info("请求到的数据为:{}",likeinfo);
        likeinfoMapper.deleteByMidAndUid(likeinfo);
    }

}
