package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.Comment;
import com.example.testapis.mapper.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CommentController {

    static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentMapper commentMapper;



    @PostMapping("comment/insert")
    @UserLoginToken
    public void insertComment(@RequestBody Comment comment){
        comment.setId(UUID.randomUUID().toString());
        logger.info("comment的信息为:{}",comment);
        commentMapper.insertSelective(comment);
    }

    @GetMapping("comment/getByMid/{id}")
    @UserLoginToken
    public Object getByMid(@PathVariable String id){
        logger.info("请求的id为:{}",id);
        HashMap<String,Object> map=new HashMap<>();
        map.put("comments",commentMapper.findAllByMediaid(id));
        return map;
    }

    @DeleteMapping("comment/delete/{id}")
    @UserLoginToken
    public Object deleteById(@PathVariable String id){
        HashMap<String ,Object> map=new HashMap<>();
        commentMapper.deleteByPrimaryKey(id);
        return map;
    }
}
