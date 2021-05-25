package com.example.weixin.controller;

import com.example.weixin.bl.RecommendService;
import com.example.weixin.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseVO addPic(Integer id,String pic){
        return recommendService.addPic(id,pic);
    }

    @ApiOperation("获取轮播图列表")
    @GetMapping("/getRecommend")
    public ResponseVO getRecommend(){
        return recommendService.getRecommend();
    }

    @ApiOperation("删除指定轮播图")
    @GetMapping("/deletePic")
    public ResponseVO deletePic(String pic){
        return recommendService.deletePic(pic);
    }
}
