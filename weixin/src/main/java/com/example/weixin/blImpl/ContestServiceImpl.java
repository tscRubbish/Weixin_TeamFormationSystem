package com.example.weixin.blImpl;

import com.example.weixin.bl.ContestService;
import com.example.weixin.data.ContestMapper;
import com.example.weixin.po.Contest;
import com.example.weixin.po.User;
import com.example.weixin.vo.ContestForm;
import com.example.weixin.vo.ContestVo;
import com.example.weixin.vo.ResponseVO;
import com.example.weixin.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContestServiceImpl implements ContestService {

    @Autowired
    ContestMapper contestMapper;

    public ResponseVO create(ContestForm contestForm){
        Contest contest=new Contest(contestForm);
        contestMapper.createContest(contest);
        ContestVo contestVo=new ContestVo(contest);
        return ResponseVO.buildSuccess(contestVo);
    }

    public ResponseVO getInfo(Integer id){
        Contest contest=contestMapper.getContestById(id);
        if (contest==null) return ResponseVO.buildFailure("查无用户");
        ContestVo contestVo=new ContestVo(contest);
        return ResponseVO.buildSuccess(contestVo);
    }

    public ResponseVO search(String word, int page){
        List<Contest> list=contestMapper.getContestByWord(word);
        if (list.size()<=(page-1)*20) return null;
        List<ContestVo> anslist=new ArrayList<ContestVo>();
        for (int i=(page-1)*20;i<page*20;i++){
            if (list.size()<=i) break;
            anslist.add(new ContestVo(list.get(i)));
        }
        return ResponseVO.buildSuccess(anslist);
    }

    public ResponseVO deleteByTime(UserVo userVo,LocalDate localDate){
        User user=new User(userVo);
        contestMapper.deleteContestByTime(user,localDate);
        return ResponseVO.buildSuccess("删除"+contestMapper.deleteContestByTime(user,localDate)+"项比赛");
    }
    public ResponseVO delete(ContestVo contestVo){
        Contest contest=new Contest(contestVo);
        if (contestMapper.deleteContest(contest)==0) return ResponseVO.buildFailure("删除信息不存在");
        return ResponseVO.buildSuccess("删除"+contest.getName()+"成功");
    }
    public ResponseVO updateInfo(ContestVo contestVo){
        Contest contest=new Contest(contestVo);
        Contest contest_old=contestMapper.getContestById(contestVo.getId());
        if (contest_old==null) return ResponseVO.buildFailure("修改不存在信息");
        if (!contest_old.getName().equals(contest.getName()))
            return ResponseVO.buildFailure("非法更改比赛名");
        if (!contest_old.getSponsorId().equals(contest.getSponsorId()))
            return ResponseVO.buildFailure("非法更改主办方");
        contestMapper.updateContest(contest);
        return ResponseVO.buildSuccess(contestVo);
    }
    public ResponseVO deletePics(Integer id,String pic){
        if (contestMapper.deleteContestPics(id,pic)==0) return ResponseVO.buildFailure("图片不存在");
        return ResponseVO.buildSuccess("删除图片:"+pic);
    }

    public ResponseVO insertPics(Integer id,String pic){
        try{
            contestMapper.insertContestPics(id,pic);
        }catch (Exception e){
            return ResponseVO.buildFailure(e.getMessage());
        }
        ContestVo contestVo=new ContestVo(contestMapper.getContestById(id));
        return ResponseVO.buildSuccess(contestVo);
    }
}
