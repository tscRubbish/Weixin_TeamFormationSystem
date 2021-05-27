package com.example.weixin.blImpl;

import com.example.weixin.bl.UserService;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.User;
import com.example.weixin.util.MD5Encryption;
import com.example.weixin.vo.ResponseVO;
import com.example.weixin.vo.UserForm;
import com.example.weixin.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public UserVo loign(UserForm userForm){
        User user=null;
        if (userForm.getEmail()!=null)
            user=userMapper.getUserByEmail(userForm.getEmail());
        else if (user==null&&userForm.getUsername()!=null){
            user=userMapper.getUserByName(userForm.getUsername());
        }
        if (user==null) return null;
        String passowrd=MD5Encryption.encrypt(userForm.getPassword());
        if (user.getPassword().equals(passowrd)||user.getPassword().equals(userForm.getPassword())) {
            UserVo userVo=new UserVo(user);
            return userVo;
        }
        return null;
    }

    public ResponseVO register(UserVo userVo){
        try {
            User user = userMapper.getUserByName(userVo.getUsername());
            if (user != null) return ResponseVO.buildFailure("用户名重复");
            user = userMapper.getUserByEmail(userVo.getEmail());
            if (user != null) return ResponseVO.buildFailure("邮箱重复");
            user=new User();
            user.setUsername(userVo.getUsername());
            user.setEmail(userVo.getEmail());
            user.setPassword(MD5Encryption.encrypt(userVo.getPassword()));
            user.setUserType(userVo.getUserType());
            userMapper.createUser(user);
            log.info("new user id is "+user.getId());
            userVo.setId(user.getId());
            return ResponseVO.buildSuccess(userVo);
        }catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseVO.buildFailure("注册失败");
        }
    }
    public ResponseVO search(String word,int page){
        List<User> list=userMapper.getUserByWords(word);
        if (list.size()<=(page-1)*20) return null;
        List<UserVo> anslist=new ArrayList<UserVo>();
        for (int i=(page-1)*20;i<page*20;i++){
            if (list.size()<=i) break;
            anslist.add(new UserVo(list.get(i)));
        }
        return ResponseVO.buildSuccess(anslist);
    }
    public ResponseVO getUserInfo(Integer id){
        User user=userMapper.getUserById(id);
        if (user==null) return ResponseVO.buildFailure("查无用户");
        return ResponseVO.buildSuccess(new UserVo(user));
    }
    public ResponseVO updateUserInfo(UserVo userVo){
        User user_old=userMapper.getUserById(userVo.getId());
        User user_new=new User(userVo);
        if (!user_old.getUsername().equals(user_new.getUsername()))
            return ResponseVO.buildFailure("非法更改用户名");
        if (!user_old.getEmail().equals(user_new.getEmail()))
            return ResponseVO.buildFailure("非法更改邮箱");
        if (!user_old.getLikes().equals(user_new.getLikes()))
            return ResponseVO.buildFailure("非法更改点赞数");
        if (!user_old.getScore().equals(user_new.getScore()))
            return ResponseVO.buildFailure("非法更改评分");
        //todo:暂无修改密码
        userMapper.updateUser(user_new);
        return ResponseVO.buildSuccess(userVo);
    }
    public ResponseVO updateScore(UserVo userVo,int score){
        userMapper.updateScore(new User(userVo),score);
        return ResponseVO.buildSuccess(userMapper.getUserById(userVo.getId()));
    }
    public ResponseVO updateLikes(UserVo userVo){
        userVo.setLikes(userVo.getLikes()+1);
        userMapper.updateLikes(new User(userVo));
        return ResponseVO.buildSuccess(userVo);
    }
    public ResponseVO delete(UserVo userVo){
        if(userMapper.deleteUser(new User(userVo))==0) return ResponseVO.buildFailure("用户不存在");
        return ResponseVO.buildSuccess("删除"+userVo.getUsername()+"成功");
    }
}
