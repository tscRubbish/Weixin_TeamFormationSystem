package com.example.weixin.po;

import com.example.weixin.vo.TeamForm;
import com.example.weixin.vo.TeamVo;
import com.example.weixin.vo.UserVo;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
@Getter
@Setter
public class Team {
    private Integer id;
    private String pic;
    private String name;
    private String password;
    private String description;
    private Integer captainId;
    private ArrayList<Integer> membersId;
    private String captainNotice;
    private Integer contestId;
    public Team(){

    }
    public Team(@NonNull TeamForm teamForm){
        name=teamForm.getName();
        password=teamForm.getPassword();
        captainId=teamForm.getCaptainId();
        contestId=teamForm.getContestId();
    }
    public Team(@NonNull TeamVo teamVo){
        id=teamVo.getId();
        pic=teamVo.getPic();
        name=teamVo.getName();
        description=teamVo.getDescription();
        captainId=teamVo.getCaptain().getId();
        membersId=new ArrayList<Integer>();
        if (teamVo.getMembers()!=null)
        for (UserVo userVo:teamVo.getMembers()){
            membersId.add(userVo.getId());
        }
        captainNotice=teamVo.getCaptainNotice();
        contestId=teamVo.getContest().getId();
    }
}
