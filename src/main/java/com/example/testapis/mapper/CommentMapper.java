package com.example.testapis.mapper;

import com.example.testapis.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}
