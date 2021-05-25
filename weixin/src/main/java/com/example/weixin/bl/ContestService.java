package com.example.weixin.bl;

import com.example.weixin.po.Contest;
import com.example.weixin.vo.ContestForm;
import com.example.weixin.vo.ContestVo;
import com.example.weixin.vo.ResponseVO;
import com.example.weixin.vo.UserVo;

import java.time.LocalDate;
import java.util.List;

public interface ContestService {

    public ResponseVO create(ContestForm contestForm);

    public ResponseVO getInfo(Integer id);

    public ResponseVO search(String word,int page);

    public ResponseVO deleteByTime(UserVo userVo,LocalDate localDate);

    public ResponseVO delete(ContestVo contestVo);

    public ResponseVO updateInfo(ContestVo contestVo);

    public ResponseVO deletePics(Integer id,String pic);

    public ResponseVO insertPics(Integer id,String pic);

}
