package com.example.weixin.vo;

import com.example.weixin.eums.UserType;
import lombok.Data;

@Data
public class UserWithTokenVo {
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
    private String njuToken;
    private String njuLongToken;
    public UserWithTokenVo(){
    }
}
