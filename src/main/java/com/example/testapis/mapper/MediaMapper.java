package com.example.testapis.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.testapis.entity.Media;
import com.example.testapis.info.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MediaMapper {
    int deleteByPrimaryKey(String id);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    List<Media> findAllByPage(PageInfo pageInfo);

    List<Media> findAll();

    Integer countById(@Param("id")String id);

    Integer count();

}
