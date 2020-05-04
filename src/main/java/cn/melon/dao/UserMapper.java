package cn.melon.dao;

import cn.melon.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAllUser();

    List<User> selectSomeUser(@Param("start_id") int start_id, @Param("end_id") int end_id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}