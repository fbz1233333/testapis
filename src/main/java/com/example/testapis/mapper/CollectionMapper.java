package com.example.testapis.mapper;
import com.example.testapis.info.I01;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.example.testapis.entity.Collection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    List<Collection> findAllByUserid(@Param("userid")String userid);

    Collection findByUseridAndMediaid(I01 i01);



}
