package com.example.testapis.controller;

import com.example.testapis.entity.User;
import com.example.testapis.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @GetMapping("user/{id}")
    public User getById(@PathVariable String id){
            return userMapper.selectByPrimaryKey(id);
    }

    @GetMapping("users")
    public Object getUsers(){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("userList",userMapper.findAll());
        return map;
    }

    @PostMapping("user/update")
    public void updateUser(@RequestBody User user){
        logger.info("请求所得User:{}",user);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
