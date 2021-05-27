package com.example.weixin.bl;

import com.example.weixin.po.Team;
import com.example.weixin.vo.*;

import java.util.List;

public interface TeamService {

    public ResponseVO createTeam(TeamForm teamForm);

    public ResponseVO getInfo(Integer id);

    public ResponseVO search(String word, int page);

    public ResponseVO updateInfo(TeamVo teamVo);

    public ResponseVO takePart(UserVo userVo,TeamVo teamVo);

    public ResponseVO getTeamList(UserVo userVo);
}
