package com.example.testapis.controller;

import com.example.testapis.entity.Media;
import com.example.testapis.mapper.MediaMapper;
import com.example.testapis.mapper.MediaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class MediaController {
    static Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    MediaMapper mediaMapper;

    @GetMapping("medias")
    public Object getMedias(){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("mediaList",mediaMapper.findAll());
        return map;
    }

    @PostMapping("media/update")
    public String updateMedia(@RequestBody Media media){
        logger.info("请求所得Media:{}",media);
        media.setUpdateDate(new Date());
        mediaMapper.updateByPrimaryKeySelective(media);
        return "success";
    }

}
