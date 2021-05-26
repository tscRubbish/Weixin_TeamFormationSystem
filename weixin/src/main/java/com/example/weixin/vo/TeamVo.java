package com.example.weixin.vo;

import com.alibaba.fastjson.JSON;
import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.TeamMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Contest;
import com.example.weixin.po.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class TeamVo {
    private Integer id;
    private String pic;
    private String name;
    private String description;
    private UserVo captain;
    private ArrayList<UserVo> members;
    private String captainNotice;
    private ContestVo contest;

    public TeamVo(){

    }
    public TeamVo(@NonNull  Team team,ContestMapper contestMapper,TeamMapper teamMapper,UserMapper userMapper){
        id=team.getId();
        pic=team.getPic();
        name=team.getName();
        description=team.getDescription();
        captain=new UserVo(userMapper.getUserById(team.getCaptainId()));
        List<Integer> list=teamMapper.getMembers(team);
        System.out.println(JSON.toJSON(list));
        if (list!=null){
            members=new ArrayList<UserVo>();
            for (int x:list){
                members.add(new UserVo(userMapper.getUserById(x)));
            }
        }
        captainNotice=team.getCaptainNotice();
        contest=new ContestVo(contestMapper.getContestById(team.getContestId()),userMapper,contestMapper);
    }
    public TeamVo(@NonNull TeamForm teamForm,UserMapper userMapper,ContestMapper contestMapper){
        name=teamForm.getName();
        captain=new UserVo(userMapper.getUserById(teamForm.getCaptainId()));
        contest=new ContestVo(contestMapper.getContestById(teamForm.getContestId()),userMapper,contestMapper);
    }
}
