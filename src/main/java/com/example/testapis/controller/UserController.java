package com.example.testapis.controller;

import com.example.testapis.entity.User;
import com.example.testapis.mapper.UserMapper;
import com.example.testapis.requests.GetColumnsSelectedRq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @PatchMapping("user/update")
    public String updateUser(@RequestBody User user){
        logger.info("请求所得User:{}",user);

//      理当作为自动配置的信息
//        user.setUpdateTime(new Date());

        userMapper.updateByPrimaryKeySelective(user);
        return "success";
    }

    @PostMapping("user/insert")
    public String insertUser(@RequestBody User user){
        logger.info("请求所得的数据User:{}",user);
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return "success";
    }

    @PostMapping("user/getByColumns")
    public Object getByColumns(@RequestBody GetColumnsSelectedRq getColumnsSelectedRq){
        HashMap<String,Object> map=new HashMap<>();

        String columns=getColumnsSelectedRq.getColumns();
        logger.info("请求所得:{}",columns);
        map.put("userList",userMapper.findColumnsSelective(columns));

        return map;
    }
}
