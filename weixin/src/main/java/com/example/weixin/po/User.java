package com.example.weixin.po;

import com.example.weixin.eums.UserType;
import com.example.weixin.vo.UserVo;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class User {
    private Integer id;
    private String pic;
    private Integer likes;//点赞数
    private String email;
    private String username;
    private String password;
    private Double score;//队友评分
    private Integer score_nums;//评分次数，用于计算平均评分
    private UserType userType;
    private String tags;
    private String description;
    public User(){

    }
    public User(@NonNull UserVo userVo){
        id=userVo.getId();
        pic=userVo.getPic();
        likes=userVo.getLikes();
        email=userVo.getEmail();
        username=userVo.getUsername();
        password=userVo.getPassword();
        score=userVo.getScore();
        userType=userVo.getUserType();
        if (userVo.getTags()==null) tags="";
        else {
            StringBuilder builder=new StringBuilder();
            for (String str:userVo.getTags()){
                builder.append(str).append(" ");
            }
            tags=builder.toString();
        }
        description=userVo.getDescription();
    }
}
