package com.example.weixin.blImpl;

import com.alibaba.fastjson.JSON;
import com.example.weixin.bl.TeamService;
import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.TeamMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Contest;
import com.example.weixin.po.Team;
import com.example.weixin.po.User;
import com.example.weixin.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamMapper teamMapper;
    @Autowired
    ContestMapper contestMapper;
    @Autowired
    UserMapper userMapper;

    public ResponseVO createTeam(TeamForm teamForm) {
        Team team=teamMapper.getTeamByCapAndContest(teamForm.getCaptainId(),teamForm.getContestId());
        if (team!=null) return ResponseVO.buildFailure("每种比赛一个人只能组一个队");

        team=new Team(teamForm);
        teamMapper.createTeam(team);
        return ResponseVO.buildSuccess(new TeamVo(team,contestMapper,teamMapper,userMapper));
    }

    public ResponseVO getInfo(Integer id){
        Team team=teamMapper.getTeamById(id);
        if (team==null) return ResponseVO.buildFailure("查无队伍");
        TeamVo teamVo=new TeamVo(team,contestMapper,teamMapper,userMapper);
        return ResponseVO.buildSuccess(teamVo);
    }

    public ResponseVO search(String word, int page){
        List<Team> list=teamMapper.getTeamByword(word);
        if (list.size()<=(page-1)*20) return null;
        List<TeamVo> anslist=new ArrayList<TeamVo>();
        for (int i=(page-1)*20;i<page*20;i++){
            if (list.size()<=i) break;
            if (list.get(i)==null) break;
            anslist.add(new TeamVo(list.get(i),contestMapper,teamMapper,userMapper));
        }
        return ResponseVO.buildSuccess(anslist);
    }
    public ResponseVO updateInfo(TeamVo teamVo){
        Team team=new Team(teamVo);
        Team team_old=teamMapper.getTeamById(teamVo.getId());
        if (team_old==null) return ResponseVO.buildFailure("修改不存在信息");
        if (!team_old.getName().equals(team.getName()))
            return ResponseVO.buildFailure("非法更改队伍名");
        if (!team_old.getCaptainId().equals(team.getCaptainId()))
            return ResponseVO.buildFailure("非法更改队长");
        if (!team_old.getContestId().equals(team.getContestId()))
            return ResponseVO.buildFailure("非法更改队伍比赛");
        teamMapper.updateTeam(team);
        return ResponseVO.buildSuccess(teamVo);
    }
    //一个人不能在一个比赛中加入多个队伍
    public ResponseVO takePart(UserVo userVo, TeamVo teamVo){
        Team team=new Team(teamVo);
        User user=userMapper.getUserById(userVo.getId());
        if (user==null) return ResponseVO.buildFailure("错误用户信息");
        try{
            if (teamMapper.selectTeamOfMemberAndContest(userVo.getId(),team.getContestId())!=null)
                return ResponseVO.buildFailure("该用户已参与该比赛的队伍");
            teamMapper.addMember(team,userVo.getId());
        }catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
        if (teamVo.getMembers()!=null)
            teamVo.getMembers().add(new UserVo(user));
        else {
            ArrayList<UserVo> list=new ArrayList<UserVo>();
            list.add(new UserVo(user));
            teamVo.setMembers(list);
        }
        return ResponseVO.buildSuccess(teamVo);
    }
    public ResponseVO getTeamList(TeamVo teamVo){
        try {
            if (teamMapper.getTeamById(teamVo.getId()) == null)
                ResponseVO.buildFailure("无此用户，无法查询队伍列表");
            Team team=new Team(teamVo);
            return ResponseVO.buildSuccess(teamMapper.getMembers(team));
        }catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
    }
}
