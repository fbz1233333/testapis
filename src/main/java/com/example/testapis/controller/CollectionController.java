package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.Collection;
import com.example.testapis.entity.User;
import com.example.testapis.info.I01;
import com.example.testapis.info.LoginInfo;
import com.example.testapis.info.PageInfo;
import com.example.testapis.mapper.CollectionMapper;
import com.example.testapis.mapper.UserMapper;
import com.example.testapis.results.FindIdAndNameByPasswordAndNameResult;
import com.example.testapis.utils.RedisUtil;
import com.example.testapis.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CollectionController {

    static Logger logger = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    CollectionMapper collectionMapper;

    @GetMapping("collection/getCollectionByUid/{id}")
    @UserLoginToken
    public Object getByUid(@PathVariable String id){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("collections",collectionMapper.findAllByUserid(id));
        return map;
    }

    @DeleteMapping("collection/delete/{id}")
    @UserLoginToken
    public void delelte(@PathVariable String  id){
        collectionMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("collection/insert")
    @UserLoginToken
    public void iii(@RequestBody Collection collection){
        logger.info("收藏信息为:{}",collection);
collection.setId(UUID.randomUUID().toString());
        collectionMapper.insert(collection);

    }

    @PostMapping("collection/getByInfo")
    @UserLoginToken
    public Object getIf(@RequestBody I01 i01)
    {
        HashMap<String,Object> map=new HashMap<>();
        boolean result=!(collectionMapper.findByUseridAndMediaid(i01)==null);
        map.put("info",result);
        return map;
    }
}
