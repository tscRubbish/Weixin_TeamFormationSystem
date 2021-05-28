package com.example.weixin.vo;

import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Recommend;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Getter
@Setter
public class RecommendVo {
    private ContestVo contestVo;
    private String pic;
    public RecommendVo(){

    }
    public RecommendVo(Recommend recommend, ContestMapper contestMapper, UserMapper userMapper){
        contestVo=new ContestVo(contestMapper.getContestById(recommend.getContestId()),userMapper,contestMapper);
        pic=recommend.getPic();
    }
}
