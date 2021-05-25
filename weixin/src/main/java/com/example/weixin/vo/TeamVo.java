package com.example.weixin.vo;

import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Contest;
import com.example.weixin.po.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

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

    @Autowired
    @JsonIgnore
    UserMapper userMapper;
    @Autowired
    @JsonIgnore
    ContestMapper contestMapper;
    public TeamVo(){

    }
    public TeamVo(@NonNull Team team){
        id=team.getId();
        pic=team.getPic();
        name=team.getName();
        description=team.getDescription();
        captain=new UserVo(userMapper.getUserById(team.getCaptainId()));
        for (Integer id:team.getMembersId()){
            members.add(new UserVo(userMapper.getUserById(id)));
        }
        captainNotice=team.getCaptainNotice();
        contest=new ContestVo(contestMapper.getContestById(team.getContestId()));
    }
    public TeamVo(@NonNull TeamForm teamForm){
        name=teamForm.getName();
        captain=new UserVo(userMapper.getUserById(teamForm.getCaptainId()));
        contest=new ContestVo(contestMapper.getContestById(teamForm.getContestId()));
    }
}
