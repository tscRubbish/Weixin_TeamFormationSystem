package com.example.weixin.data;

import com.example.weixin.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper{

    /**
     * 添加新用户
     * 返回添加状态，id在user对象中
     */
    int createUser(User user);

    /**
     * 根据ID查询指定用户
     */
    User getUserById(@Param("id") int id);

    /**
     * 根据name查询指定用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 根据email查询指定用户
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 根据关键词在用户名 简介，标签中查找
     */
    List<User> getUserByWords(String word);

    /**
     *  修改用户信息
     *  可修改：密码，描述，标签，图片
     */
    int updateUser(User user);


    /**
     *  修改用户评分
     *  可修改：评分，评价次数
     */
    int updateScore(@Param("user") User user, @Param("score") int score);

    /**
     *  修改用户点赞
     *  可修改：点赞次数
     */
    int updateLikes(@Param("user") User user);

    /**
     * 删除用户，禁止非Admin用户调用
     * @param user
     * @return
     */
    int deleteUser(@Param("user") User user);
}
