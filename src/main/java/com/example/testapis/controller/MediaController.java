package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.Media;
import com.example.testapis.info.KindAndHotLimit;
import com.example.testapis.info.MixedInfo;
import com.example.testapis.info.PageInfo;
import com.example.testapis.mapper.MediaMapper;
import com.example.testapis.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
    @Value("${media.image.default}")
    String IMG_DEFAULT;
    @PostMapping("media/insert")
    @UserLoginToken
    public String insertMedia(@RequestBody Media media){
        logger.info("请求所得的数据Media:{}",media);
        media.setId(UUID.randomUUID().toString());
        media.setImageinfo(IMG_DEFAULT);
        media.setUpdateTime(new Date());
        media.setState(state_draft);
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


    @Value("${file.image.path}")
    String BASIC_PATH;

    @Value("${file.image.name}")
    Integer random_name;

    @Value("${media.state.complete}")
    String state_complete;

    @Autowired
    RandomUtils randomUtils;

    @PostMapping("media/upload/{id}")
        public void upload(@RequestBody MultipartFile file,@PathVariable String id) throws IOException {
            logger.info("请求的id:{}",id);
            String fileName=file.getOriginalFilename();
            String new_name=randomUtils.getRandomString(random_name);


            String suffix= null;
            if (fileName != null) {
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            }

            String new_file_name=new_name+'.'+suffix;

            String FileTo=BASIC_PATH+new_file_name;
            logger.info(FileTo);
            File to=new File(FileTo);
            file.transferTo(to);

            Media media=new Media();
            media.setId(id);
            media.setImageinfo(new_file_name);
            media.setState(state_complete);
            mediaMapper.updateByPrimaryKeySelective(media);








        }

    @Value("${media.state.draft}")
    String state_draft;


    @GetMapping("media/findDrafts/{id}")
    @UserLoginToken
    public Object getDrafts(@PathVariable String  id){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("myDrafts",mediaMapper.findAllByStateAndUserid(state_draft,id));
        map.put("myCompletes",mediaMapper.findAllByStateAndUserid(state_complete,id));

        return map;
    }

    @DeleteMapping("media/delete/{id}")
    @UserLoginToken
    public void delete(@PathVariable String  id){
        mediaMapper.deleteByPrimaryKey(id);
    }
}
