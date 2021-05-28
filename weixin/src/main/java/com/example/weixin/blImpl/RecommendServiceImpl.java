package com.example.weixin.blImpl;

import com.example.weixin.bl.RecommendService;
import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.RecommendMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Contest;
import com.example.weixin.po.Recommend;
import com.example.weixin.vo.RecommendVo;
import com.example.weixin.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendMapper recommendMapper;
    @Autowired
    ContestMapper contestMapper;
    @Autowired
    UserMapper userMapper;

    public ResponseVO addPic(Integer id,String pic){
        if (id<=0) return ResponseVO.buildFailure("无效比赛Id");
        Contest contest=contestMapper.getContestById(id);
        if (contest==null) return ResponseVO.buildFailure("无该比赛");
        if (pic.equals("")||pic==null) return ResponseVO.buildFailure("添加空轮播图");
        recommendMapper.addRecommend(id,pic);
        return ResponseVO.buildSuccess("添加"+contest.getName()+"的轮播图:"+pic+"成功");
    }

    public ResponseVO getRecommend(){
        List<Recommend> pics=recommendMapper.getRecommend();
        List<RecommendVo> list=new ArrayList<RecommendVo>();
        for (Recommend recommend:pics){
            list.add(new RecommendVo(recommend,contestMapper,userMapper));
        }
        return ResponseVO.buildSuccess(list);
    }

    public ResponseVO deletePic(String pic){
        if (pic=="") return ResponseVO.buildFailure("删除空轮播图");
        if (recommendMapper.deleteRecommendByPic(pic)==0) return ResponseVO.buildFailure("图片不存在");
        return ResponseVO.buildSuccess("删除图片:"+pic+"成功");
    }
}
