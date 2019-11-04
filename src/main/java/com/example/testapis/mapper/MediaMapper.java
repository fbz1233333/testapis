package com.example.testapis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.testapis.entity.Media;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MediaMapper {
    int deleteByPrimaryKey(String id);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    List<Media> findAll();


}
