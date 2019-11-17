package com.example.testapis.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.testapis.entity.History;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

    List<History> findAllByUserid(@Param("userid")String userid);


}
