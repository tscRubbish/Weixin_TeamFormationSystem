package com.example.weixin.data;

import com.example.weixin.po.Contest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecommendMapper {
    /**
     * 添加推荐比赛海报
     * @return ID
     */
    int addRecommend(@Param("id") Integer id,@Param("pic") String pic);
    /**
     * 获取推荐比赛海报
     * @return ID
     */
    List<String> getRecommend();
    /**
     * 删除指定海报
     * @return ID
     */
    int deleteRecommendByPic(String pic);
}
