package com.example.testapis.mapper;

import com.example.testapis.entity.User;
import com.example.testapis.info.PageInfo;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAllByIsDel(@Param("isDel") Integer isDel);

    List<User> findAll();

    List<User> findColumnsSelective(@Param("columns")String columns);

    List<User> findAllByPage(PageInfo pageInfo);

     Integer count();


}
