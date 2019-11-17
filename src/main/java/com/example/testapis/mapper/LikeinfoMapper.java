package com.example.testapis.mapper;
import java.util.List;

import com.example.testapis.entity.Likeinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeinfoMapper {
    int deleteByPrimaryKey(@Param("uid") String uid, @Param("id") String id);

    int insert(Likeinfo record);

    int insertSelective(Likeinfo record);

    Likeinfo selectByPrimaryKey(@Param("uid") String uid, @Param("id") String id);

    int updateByPrimaryKeySelective(Likeinfo record);

    int updateByPrimaryKey(Likeinfo record);

    int deleteByPrimaryKey(String uid);

    Likeinfo selectByPrimaryKey(String uid);

    Likeinfo findAllByMidAndUid(Likeinfo likeinfo);

    int deleteByMidAndUid(Likeinfo likeinfo);


}
