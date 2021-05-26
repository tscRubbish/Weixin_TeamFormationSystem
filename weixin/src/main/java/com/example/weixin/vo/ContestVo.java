package com.example.weixin.vo;

import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Contest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Data
@Getter
@Setter
public class ContestVo {
    private Integer id;
    private String name;
    private UserVo sponsor;
    private LocalDate startTime;
    private LocalDate endTime;
    private String description;
    private String[] tags;
    private List<String> pics;

    public ContestVo(){

    }
    public ContestVo(@NonNull ContestForm contestForm){
        name=contestForm.getName();
        sponsor=contestForm.getSponsor();
        startTime=contestForm.getStartTime();
        endTime=contestForm.getEndTime();
    }
    public ContestVo(@NonNull Contest contest,UserMapper userMapper,ContestMapper contestMapper){
        id=contest.getId();
        name=contest.getName();
        sponsor=new UserVo(userMapper.getUserById(contest.getSponsorId()));
        startTime=contest.getStartTime();
        endTime=contest.getEndTime();
        description=contest.getDescription();
        tags=contest.getTags().split(" ");
        pics=contestMapper.getPics(contest);
    }
}
