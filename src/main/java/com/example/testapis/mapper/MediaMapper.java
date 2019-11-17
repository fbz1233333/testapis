package com.example.testapis.mapper;

import com.example.testapis.entity.Media;
import com.example.testapis.info.KindAndHotLimit;import com.example.testapis.info.PageInfo;import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

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

    Integer countById(@Param("id") String id);

    Integer count();

    List<Media> findAllByKindInfoAndHot(KindAndHotLimit kindAndHotLimit);

    List<Media> findAllByStateAndUserid(@Param("state")String state,@Param("userid")String userid);

    int updateImageinfoById(@Param("updatedImageinfo")String updatedImageinfo,@Param("id")String id);



}
