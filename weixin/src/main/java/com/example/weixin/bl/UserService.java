package com.example.weixin.bl;


import com.example.weixin.po.User;
import com.example.weixin.vo.ResponseVO;
import com.example.weixin.vo.UserForm;
import com.example.weixin.vo.UserVo;

import java.util.List;

public interface UserService {

    public UserVo loign(UserForm userForm);

    public ResponseVO register(UserVo userVo);

    public ResponseVO search(String word,int page);

    public ResponseVO getUserInfo(Integer id);

    public ResponseVO updateUserInfo(UserVo userVo);

    public ResponseVO updateScore(UserVo userVo,int score);

    public ResponseVO updateLikes(UserVo userVo);

    public ResponseVO delete(UserVo userVo);
}
