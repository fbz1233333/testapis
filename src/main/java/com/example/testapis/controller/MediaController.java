package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.Media;
import com.example.testapis.info.KindAndHotLimit;
import com.example.testapis.info.MixedInfo;
import com.example.testapis.info.PageInfo;
import com.example.testapis.mapper.MediaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MediaController {

    static Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    MediaMapper mediaMapper;


    @GetMapping("media/{id}")
    public Media getById(@PathVariable String id){
        return mediaMapper.selectByPrimaryKey(id);
    }

    @GetMapping("medias")
    public Object getMedias(){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("mediaList",mediaMapper.findAll());
        return map;
    }

    @PatchMapping("media/update")
    public String updateMedia(@RequestBody Media media){
        logger.info("请求所得Media:{}",media);

//      理当作为自动配置的信息
//        media.setUpdateTime(new Date());

        mediaMapper.updateByPrimaryKeySelective(media);
        return "success";
    }

    @PostMapping("media/insert")
    @UserLoginToken
    public String insertMedia(@RequestBody Media media){
        logger.info("请求所得的数据Media:{}",media);
        media.setId(UUID.randomUUID().toString());
        media.setImageinfo("default.png");
        media.setUpdateTime(new Date());
        mediaMapper.insert(media);
        return "success";
    }


    @PostMapping("media/getByPage")
    public Object getMediasByPages(@RequestBody PageInfo pageInfo){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("mediaList",mediaMapper.findAllByPage(pageInfo));
        map.put("mediaCount",mediaMapper.count());
        return map;
    }

//    @PostMapping("media/getMediaHot")
//    public Object getMediaByKindAndHot(@RequestBody KindAndHotLimit kindAndHotLimit){
//        logger.info("获取的Info为:{}",kindAndHotLimit);
//        HashMap<String,Object> map=new HashMap<>();
//        map.put("mediaList",mediaMapper.findAllByKindInfoAndHot(kindAndHotLimit));
//        return map;
//    }


    @PostMapping("media/getMixedMediaLimit")
    public Object getMixedMedia(@RequestBody MixedInfo mixedInfo){



        logger.info("获取的info为:{}",mixedInfo);
        HashMap<String,Object> map=new HashMap<>();
        for (String kindStr:mixedInfo.getMixedStr()){
//            logger.info("当前的kind为:{}",kindStr);


            KindAndHotLimit kindAndHotLimit=new KindAndHotLimit();
            kindAndHotLimit.setKind(kindStr);
            kindAndHotLimit.setLimit(mixedInfo.getLimit());
            List<Media> medias=mediaMapper.findAllByKindInfoAndHot(kindAndHotLimit);


//            logger.info("请求所得medias:{}",medias);
            map.put(kindStr.toLowerCase()+"List",medias);
        }

        return map;
    }

}
