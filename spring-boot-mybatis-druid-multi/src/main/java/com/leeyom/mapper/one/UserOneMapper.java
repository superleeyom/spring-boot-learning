package com.leeyom.mapper.one;

import com.leeyom.mapper.UserSql;
import com.leeyom.param.UserParam;
import com.leeyom.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserOneMapper {

    @Delete("DELETE FROM users WHERE id =#{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert("INSERT INTO users(user_name,password,user_sex,nick_name) VALUES(#{userName}, #{password}, #{userSex} ,#{nickName})")
    int insert(User record);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex", column = "user_sex"),
            @Result(property = "nickName", column = "nick_name")
    })
    List<User> selectAll();

    @Update("UPDATE users SET user_name=#{userName},password=#{password},user_sex=#{userSex},nick_name=#{nickName} WHERE id =#{id}")
    int updateByPrimaryKey(User record);

    @SelectProvider(type = UserSql.class, method = "getUserListByPage")
    List<User> getUserListByPage(UserParam userParam);


    @SelectProvider(type = UserSql.class, method = "getCount")
    Long getCount(UserParam userParam);
}