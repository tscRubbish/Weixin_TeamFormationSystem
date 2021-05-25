package com.example.weixin.data;

import com.example.weixin.po.Team;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamMapper {

    /**
     * 创建Team
     * @param team
     * @return id
     */
    int createTeam(Team team);

    /**
     * 根据id查找Team
     * @param id
     * @return team
     */
    Team getTeamById(@Param("id") int id);

    /**
     * 根据word查找Team
     * 查找返回 描述，name
     * @param word
     * @return team
     */
    List<Team> getTeamByword(String word);

    /**
     * 根据队长id和比赛id查找Team，限制每人一种比赛只能组一个队
     * 查找返回 描述，name
     * @param captainId,contestId
     * @return team
     */
    Team getTeamByCapAndContest(Integer captainId,Integer contestId);

    /**
     * 更新队伍信息
     * 可更新 队长公告，pic，password
     * @param team
     * @return 更新结果
     */
    int updateTeam(Team team);

    /**
     * 删除队伍
     * @param team
     * @return 删除结果
     */
    int deleteTeam(Team team);

    /**
     * 增加单个成员
     * @param memberId
     * @return
     */
    int addMember(@Param("team") Team team, @Param("memberId") int memberId);

    /**
     * 删除单个成员
     * @param memberId
     * @return
     */
    int deleteMember(@Param("team") Team team, @Param("memberId") int memberId);

    List<Integer> getMembers(Team team);

    /**
     * 检查member是否在contest中有队伍存在
     * @param
     * @return
     */
    Team selectTeamOfMemberAndContest(@Param("member") Integer member,@Param("contest") Integer contest);
}
