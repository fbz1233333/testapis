package com.example.testapis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

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

    List<Comment> findAllByMediaid(@Param("mediaid")String mediaid);


}
