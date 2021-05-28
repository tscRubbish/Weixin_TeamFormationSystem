package com.example.weixin.po;

import com.example.weixin.vo.ContestForm;
import com.example.weixin.vo.ContestVo;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Getter
@Setter
public class Contest {
    private Integer id;
    private String name;
    private Integer sponsorId;//主办方id
    private LocalDate startTime;
    private LocalDate endTime;
    private String description;
    private String tags;
    public Contest(){

    }
    public Contest(@NonNull ContestForm contestForm){
        name=contestForm.getName();
        sponsorId=contestForm.getSponsor().getId();
        startTime=contestForm.getStartTime();
        endTime=contestForm.getEndTime();
    }
    public Contest(@NonNull ContestVo contestVo){
        id=contestVo.getId();
        name=contestVo.getName();
        sponsorId=contestVo.getSponsor().getId();
        startTime=contestVo.getStartTime();
        endTime=contestVo.getEndTime();
        description=contestVo.getDescription();
        StringBuilder stringBuilder=new StringBuilder();
        for (String str:contestVo.getTags()){
            stringBuilder.append(str+" ");
        }
        tags=stringBuilder.toString();
    }

}
