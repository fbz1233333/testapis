package com.example.testapis.controller;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.entity.Collection;
import com.example.testapis.entity.Next;
import com.example.testapis.info.I01;
import com.example.testapis.mapper.CollectionMapper;
import com.example.testapis.mapper.NextMapper;
import com.example.testapis.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class NextController {

    static Logger logger = LoggerFactory.getLogger(NextController.class);

    @Autowired
    NextMapper nextMapper;

    @GetMapping("next/mixedInfo")
    public Object getMixed(){
        HashMap<String ,Object> map=new HashMap<>();
        map.put("musics",nextMapper.findAllByKind("MUSIC"));
        return map;
    }

    @Value("${file.image.path}")
    String IMG_PATH;

    @Value("${file.image.cache}")
    String IMG_CACHE;

    @Autowired
    RandomUtils randomUtils;

    @Value("${file.image.name}")
    Integer random_name;

    @PostMapping("next/upload")
    @UserLoginToken
    public Object upload(@RequestBody MultipartFile file) throws IOException {
        HashMap<String ,Object> map=new HashMap<>();
        logger.info(file.getResource().toString());
        logger.info(file.getOriginalFilename());
        logger.info(IMG_CACHE);
        String new_name=randomUtils.getRandomString(random_name);
        String fileName=file.getOriginalFilename();
        String suffix= null;
        if (fileName != null) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        String new_file_name=new_name+'.'+suffix;


        File to=new File(IMG_CACHE+new_file_name);

        file.transferTo(to);
        map.put("fileName",new_file_name);
        return map;
    }


    @PostMapping("next/insert")
    @UserLoginToken
    public void insert(@RequestBody Next next) throws IOException {
        logger.info("请求的nextInfo:{}",next);
        next.setId(UUID.randomUUID().toString());

        String cache_path=IMG_CACHE+next.getCover();
        String image_path=IMG_PATH+next.getCover();
        logger.info(cache_path);
        logger.info(image_path);
        File f=new File(image_path);
        File f2=new File(cache_path);



        FileCopyUtils.copy(f2,f);

        nextMapper.insertSelective(next);
    }
}
