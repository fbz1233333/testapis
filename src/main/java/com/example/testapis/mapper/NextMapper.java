package com.example.testapis.mapper;

import com.example.testapis.entity.Next;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface NextMapper {
    int deleteByPrimaryKey(String id);

    int insert(Next record);

    int insertSelective(Next record);

    Next selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Next record);

    int updateByPrimaryKey(Next record);

    List<Next> findAllByKind(@Param("kind") String kind);
}