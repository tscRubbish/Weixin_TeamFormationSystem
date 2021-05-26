package com.example.weixin.controller.user;

import com.example.weixin.bl.UserService;
import com.example.weixin.eums.UserType;
import com.example.weixin.po.User;
import com.example.weixin.util.JwtUtil;
import com.example.weixin.vo.ResponseVO;
import com.example.weixin.vo.UserForm;
import com.example.weixin.vo.UserVo;
import com.example.weixin.vo.UserWithTokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/api/user")
@Api(tags = "UserController用户接口")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    private Integer shortTokenTime = 20;

    private Integer longTokenTime = 60;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm){
        UserVo uservo=userService.loign(userForm);
        if (uservo==null){
            return ResponseVO.buildFailure("邮箱或密码错误");
        }
        UserWithTokenVo userWithToken = new UserWithTokenVo();
        BeanUtils.copyProperties(uservo,userWithToken);
        userWithToken.setNjuToken(JwtUtil.createToken(uservo.getId(),uservo.getUserType().getValue(),shortTokenTime));
        userWithToken.setNjuLongToken(JwtUtil.createToken(uservo.getId(),uservo.getUserType().getValue(),longTokenTime));
        return ResponseVO.buildSuccess(userWithToken);
    }
    @ApiOperation("注册")
    @PostMapping("/register")
    public ResponseVO register(@RequestBody UserVo userVo){
        return userService.register(userVo);
    }

    @ApiOperation("搜索用户")
    @GetMapping("/search")
    public ResponseVO search(String word, int page){
        if (page<=0) return null;
        return userService.search(word,page);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getInfo")
    public ResponseVO getInfo(int id){
        if (id<=0) ResponseVO.buildFailure("无效id");
        return userService.getUserInfo(id);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/changeInfo")
    public ResponseVO changeInfo(@RequestBody UserVo userVo,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null||userId!=userVo.getId()){
            return ResponseVO.buildFailure("未登录或无更改权限");
        }
        return userService.updateUserInfo(userVo);
    }

    @ApiOperation("评价用户")
    @PostMapping("/updateScores")
    //todo:此处未实现评分记录，所以可以无限评分
    public ResponseVO updateScores(@RequestBody UserVo userVo,int score,HttpServletRequest request){
        if (score<0||score>5) return ResponseVO.buildFailure("非法评分分数");
        if (userVo==null) return ResponseVO.buildFailure("更新传入空用户");
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null||userId==userVo.getId()){
            return ResponseVO.buildFailure("无法给自己评分");
        }
        return userService.updateScore(userVo,score);
    }

    @ApiOperation("点赞用户")
    @PostMapping("/updateLikes")
    //todo:此处未实现点赞记录，所以可以无限点赞
    public ResponseVO updateLikes(@RequestBody UserVo userVo,HttpServletRequest request){
        if (userVo==null) return ResponseVO.buildFailure("更新传入空用户");
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null||userId==userVo.getId()){
            return ResponseVO.buildFailure("无法给自己点赞");
        }
        return userService.updateLikes(userVo);
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public ResponseVO delete(@RequestBody UserVo userVo,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        Integer userType=JwtUtil.verifyTokenAndGetUserType(token);
        if (userId==null||userType != UserType.Admin.getValue()){
            return ResponseVO.buildFailure("非管理员用户无法删除用户");
        }
        return userService.updateLikes(userVo);
    }
}
