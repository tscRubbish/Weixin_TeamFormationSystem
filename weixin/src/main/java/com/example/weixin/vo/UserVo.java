package com.example.weixin.vo;

import com.example.weixin.eums.UserType;
import com.example.weixin.po.User;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
@Data
public class UserVo {
    private Integer id;
    private String pic;
    private Integer likes;//点赞数
    private String email;
    private String username;
    private String password;
    private Double score;//队友评分
    private UserType userType;
    private String[] tags;
    private String description;
    public UserVo(){

    }
    public UserVo(@NonNull User user){
        id=user.getId();
        pic=user.getPic();
        likes=user.getLikes();
        email=user.getEmail();
        username=user.getUsername();
        password=user.getPassword();
        score=user.getScore();
        userType=user.getUserType();
        tags=user.getTags().split(" ");
        description=user.getDescription();
    }
}
