package com.example.weixin.vo;

import com.example.weixin.eums.UserType;
import lombok.Data;

@Data
public class UserWithTokenVo {
    private UserVo userVo;
    private String njuToken;
    private String njuLongToken;
    public UserWithTokenVo(){
    }
}
