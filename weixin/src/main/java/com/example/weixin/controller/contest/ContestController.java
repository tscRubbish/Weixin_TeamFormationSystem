package com.example.weixin.controller.contest;

import com.example.weixin.bl.ContestService;
import com.example.weixin.eums.UserType;
import com.example.weixin.po.Contest;
import com.example.weixin.util.JwtUtil;
import com.example.weixin.vo.ContestForm;
import com.example.weixin.vo.ContestVo;
import com.example.weixin.vo.ResponseVO;
import com.example.weixin.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/api/contest")
@Api(tags = "ContestController比赛接口")
@Slf4j
public class ContestController {
    @Autowired
    ContestService contestService;

    @ApiOperation("按指定顺序获得比赛列表")
    @GetMapping("/getList")
    public ResponseVO getList(@RequestParam(name = "word")String word,@RequestParam(name = "page") Integer page){
        if (page<=0) return null;
        return contestService.search(word,page);
        //todo:暂未实现指定排序方法
    }

    @ApiOperation("获取比赛信息")
    @GetMapping("/getInfo")
    public ResponseVO getInfo(@RequestParam(name = "id") Integer id){
        if (id<=0) return ResponseVO.buildFailure("无效id");
        return contestService.getInfo(id);
    }

    @ApiOperation("修改比赛信息")
    @PostMapping("/updateInfo")
    public ResponseVO updateInfo(@RequestBody ContestVo contestVo, HttpServletRequest request){
        if (contestVo==null) return ResponseVO.buildFailure("空比赛信息");
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null||userId!=contestVo.getSponsor().getId()){
            return ResponseVO.buildFailure("非主办方无法修改比赛信息");
        }
        return contestService.updateInfo(contestVo);
    }

    @ApiOperation("创建比赛")
    @PostMapping("/create")
    public ResponseVO create(@RequestBody ContestForm contestForm,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
        if (userType==null||userType!= UserType.Manager.getValue()){
            return ResponseVO.buildFailure("无创建比赛权限");
        }
        return contestService.create(contestForm);
    }

    @ApiOperation("删除过时比赛")
    @PostMapping("/deleteOutDate")
    public ResponseVO deleteOutDate(@RequestBody UserVo userVo,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId=JwtUtil.verifyTokenAndGetUserId(token);
        Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
        if (userType==null||userType!= UserType.Manager.getValue()||userId!=userVo.getId()){
            return ResponseVO.buildFailure("无删除比赛权限");
        }
        return contestService.deleteByTime(userVo, LocalDate.now());
    }

    @ApiOperation("删除指定比赛")
    @PostMapping("/delete")
    public ResponseVO delete(@RequestBody ContestVo contestVo,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId=JwtUtil.verifyTokenAndGetUserId(token);
        Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
        if (userType==null||userType!= UserType.Manager.getValue()||userId!=contestVo.getSponsor().getId()){
            return ResponseVO.buildFailure("无删除比赛权限");
        }
        return contestService.delete(contestVo);
    }

    @ApiOperation("删除指定图片")
    @PostMapping("/deletePics")
    public ResponseVO deletePics(@RequestBody ContestVo contestVo,@RequestParam(name = "pic") String pic,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId=JwtUtil.verifyTokenAndGetUserId(token);
        Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
        if (userType==null||userType!= UserType.Manager.getValue()||userId!=contestVo.getSponsor().getId()){
            return ResponseVO.buildFailure("无删除比赛海报图权限");
        }
        return contestService.deletePics(contestVo.getId(),pic);
    }

    @ApiOperation("加入指定图片")
    @PostMapping("/insertPics")
    public ResponseVO insertPics(@RequestBody ContestVo contestVo,@RequestParam(name = "pic") String pic,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId=JwtUtil.verifyTokenAndGetUserId(token);
        Integer userType = JwtUtil.verifyTokenAndGetUserType(token);
        if (userId==null||userType!= UserType.Manager.getValue()||userId!=contestVo.getSponsor().getId()){
            return ResponseVO.buildFailure("无添加比赛海报图权限");
        }
        return contestService.insertPics(contestVo.getId(),pic);
    }

}
