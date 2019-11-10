package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.User;
import com.example.testapis.info.LoginInfo;
import com.example.testapis.info.PageInfo;
import com.example.testapis.info.TokenInfo;
import com.example.testapis.mapper.UserMapper;
import com.example.testapis.results.FindIdAndNameByPasswordAndNameResult;
import com.example.testapis.utils.RedisUtil;
import com.example.testapis.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.ImagingOpException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    RedisUtil redisUtil;

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



    @PostMapping("user/getByPage")
    public Object getUsersByPages(@RequestBody PageInfo pageInfo){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("userList",userMapper.findAllByPage(pageInfo));
        map.put("userCount",userMapper.count());

        return map;
    }

    @PostMapping("user/login")
    public Object login(@RequestBody LoginInfo loginInfo){
        logger.info("loginInfo:{}",loginInfo);
        HashMap<String ,Object> map=new HashMap<>();


        FindIdAndNameByPasswordAndNameResult result=userMapper.findIdAndNameByPasswordAndName(loginInfo);

        if (result==null){
            logger.info("没有这样的用户:{}",loginInfo);
            map.put("errorInfo","no such user");
        }else{
            logger.info("用户信息为:{}",result);
            String token=tokenUtils.getToken(result);
            redisUtil.getAndSet(result.getId(),token);
            map.put("userInfo",result);
            map.put("token",token);
        }

        return map;
    }

    @GetMapping("user/uuuu")
    @UserLoginToken
    public String info(){
        return "dddd";
    }
}
