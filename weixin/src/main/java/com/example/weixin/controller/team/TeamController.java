package com.example.weixin.controller.team;

import com.example.weixin.bl.TeamService;
import com.example.weixin.bl.UserService;
import com.example.weixin.util.JwtUtil;
import com.example.weixin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/api/team")
@Api(tags = "TeamController队伍接口")
@Slf4j
public class TeamController {
    @Autowired
    TeamService teamService;

    @ApiOperation("搜索队伍")
    @GetMapping("/search")
    public ResponseVO search(String word, Integer page){
        if (page<=0) return null;
        return teamService.search(word,page);
        //todo:暂未实现指定排序方法
    }

    @ApiOperation("获得队伍信息")
    @GetMapping("/getInfo")
    public ResponseVO getInfo(Integer id){
        if (id<=0) return ResponseVO.buildFailure("无效队伍id");
        return teamService.getInfo(id);
    }

    @ApiOperation("修改队伍信息")
    @PostMapping("/changeInfo")
    public ResponseVO changeInfo(@RequestBody TeamVo teamVo, HttpServletRequest request){
        if (teamVo==null) return ResponseVO.buildFailure("空队伍信息");
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null||userId!=teamVo.getCaptain().getId()){
            return ResponseVO.buildFailure("非队长无法修改队伍信息");
        }
        return teamService.updateInfo(teamVo);
    }

    @ApiOperation("创建队伍")
    @GetMapping("/create")
    public ResponseVO create(TeamForm teamForm,HttpServletRequest request){
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null){
            return ResponseVO.buildFailure("请先登录在创建队伍");
        }
        return teamService.createTeam(teamForm);
    }

    @ApiOperation("加入队伍")
    @PostMapping("/takepart")
    public ResponseVO takepart(@RequestBody UserVo userVo,@RequestBody TeamVo teamVo,HttpServletRequest request){
        if (userVo==null) return ResponseVO.buildFailure("空用户加入队伍");
        if (teamVo==null) return ResponseVO.buildFailure("用户加入空队伍");
        String token = request.getHeader(JwtUtil.TOKEN_NAME);
        Integer userId = JwtUtil.verifyTokenAndGetUserId(token);
        if (userId==null){
            return ResponseVO.buildFailure("请先登录再尝试加入队伍");
        }
        return teamService.takePart(userVo,teamVo);
    }

    @ApiOperation("获得个人队伍列表")
    @PostMapping("/getTeamList")
    public ResponseVO getTeamList(@RequestBody TeamVo teamVo){
       return teamService.getTeamList(teamVo);
    }

}
