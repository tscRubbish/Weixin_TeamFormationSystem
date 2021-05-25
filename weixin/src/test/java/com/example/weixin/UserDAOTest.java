package com.example.weixin;

import com.alibaba.fastjson.JSON;
import com.example.weixin.data.UserMapper;
import com.example.weixin.eums.UserType;
import com.example.weixin.po.User;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class UserDAOTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testCreateUser01(){
        User user=new User();
        user.setUsername("ry");
        user.setPassword("123456");
        user.setEmail("654321@qq.com");
        user.setUserType(UserType.User);
        userMapper.createUser(user);
        int id=user.getId();
        System.out.println(id);
    }
    @Test
    public void testCreateUser02(){
        User user=new User();
        user.setUsername("wtt");
        user.setPassword("123456789101");
        user.setEmail("65432114551@qq.com");
        user.setUserType(UserType.User);
        int id=userMapper.createUser(user);
        System.out.println(id);
    }

    @Test
    public void testgetUserById01(){
        User user1=userMapper.getUserById(1);
        User user2=userMapper.getUserById(2);
        User user3=userMapper.getUserById(3);
        User user4=userMapper.getUserById(6);
        System.out.println(JSON.toJSON(user1));
        System.out.println(JSON.toJSON(user2));
        System.out.println(JSON.toJSON(user3));
        System.out.println(JSON.toJSON(user4));
    }
    @Test
    public void testgetUserByWord01(){
        List<User> users=userMapper.getUserByWords("编程");
        System.out.println(JSON.toJSON(users));
    }
    @Test
    public void testgetUserByName(){
        User users=userMapper.getUserByName("Tsc");
        System.out.println(JSON.toJSON(users));
    }
    @Test
    public void testupdateUser01(){
        User user=userMapper.getUserById(6);
        System.out.println(JSON.toJSON(user));
        user.setTags("软件 编程");
        user.setDescription("tqlyyds114514");
        userMapper.updateUser(user);
        User user2=userMapper.getUserById(6);
        System.out.println(JSON.toJSON(user2));
    }
    @Test
    public void testupdateUser02(){
        User user=userMapper.getUserById(6);
        System.out.println(JSON.toJSON(user));
        userMapper.updateScore(user,5);
        User user2=userMapper.getUserById(6);
        System.out.println(JSON.toJSON(user2));
        userMapper.updateScore(user,4);
        User user3=userMapper.getUserById(6);
        System.out.println(JSON.toJSON(user3));
    }
    @Test
    public void testdelete01(){
        User user=userMapper.getUserById(7);
        System.out.println(JSON.toJSON(user));
        userMapper.deleteUser(user);
    }
}
