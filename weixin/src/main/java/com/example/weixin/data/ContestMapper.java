package com.example.weixin.data;

import com.example.weixin.po.Contest;
import com.example.weixin.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface ContestMapper {

    /**
     * 创建比赛
     * @return ID
     */
    int createContest(Contest contest);

    /**
     * 更新比赛海报
     * @param pic
     * @return
     */
    int insertContestPics(int id,String pic);
    int deleteContestPics(int id,String pic);
    /**
     * 删除指定比赛
     * @param contest
     * @return 删除结果
     */
    int deleteContest(Contest contest);

    /**
     * 删除过期比赛
     * @param time
     * @return
     */
    int deleteContestByTime(@Param("user") User user,@Param("time") LocalDate time);

    /**
     * 修改比赛信息
     * @param contest
     * @return
     */
    int updateContest(Contest contest);

    /**
     * 根据id搜索比赛
     * @param id
     * @return
     */
    Contest getContestById(@Param("id") int id);

    /**
     * 根据word关键词搜索比赛
     * @param word
     * @return
     */
    List<Contest> getContestByWord(String word);

    /**
     * 得到图片列表
     * @param contest
     * @return
     */
    List<String> getPics(Contest contest);
}
