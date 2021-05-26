package com.example.weixin.controller;

import com.example.weixin.bl.RecommendService;
import com.example.weixin.eums.UserType;
import com.example.weixin.util.JwtUtil;
import com.example.weixin.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/api/recommend")
@Api(tags = "ContestController首页推荐比赛轮播图接口")
@Slf4j
public class RecommendController {
    @Autowired
    RecommendService recommendService;

    @ApiOperation("添加轮播图片")
    @GetMapping("/addPic")
    public ResponseVO addPic(@PathVariable Integer id,@PathVariable String pic, HttpServletRequest request){
            String token = request.getHeader(JwtUtil.TOKEN_NAME);
            Integer userId=JwtUtil.verifyTokenAndGetUserId(token);
            Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
            if (userType==null||userType!= UserType.Admin.getValue()){
                return ResponseVO.buildFailure("非管理员权限，无法修改推荐比赛图片");
            }
        return recommendService.addPic(id,pic);
    }

    @ApiOperation("获取轮播图列表")
    @GetMapping("/getRecommend")
    public ResponseVO getRecommend(){
        return recommendService.getRecommend();
    }

    @ApiOperation("删除指定轮播图")
    @GetMapping("/deletePic")
    public ResponseVO deletePic(@PathVariable String pic, HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId=JwtUtil.verifyTokenAndGetUserId(token);
        Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
        if (userType==null||userType!= UserType.Admin.getValue()){
            return ResponseVO.buildFailure("非管理员权限，无法删除推荐比赛图片");
        }
        return recommendService.deletePic(pic);
    }
}
