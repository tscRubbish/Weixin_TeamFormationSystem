package com.example.weixin;

import com.alibaba.fastjson.JSON;
import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.UserMapper;
import com.example.weixin.po.Contest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContestDAOTest {

    @Autowired
    ContestMapper contestMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testCreateContest(){
        Contest contest=new Contest();
        contest.setDescription("no des");
        contest.setSponsorId(1);
        contest.setEndTime(LocalDate.of(2021,5,21));
        contest.setName("创新杯");
        contest.setStartTime(LocalDate.of(2021,5,20));
        contest.setTags("编程 创新");
        contestMapper.createContest(contest);
        contestMapper.insertContestPics(contest.getId(),"123456");
        contestMapper.insertContestPics(contest.getId(),"78910");
    }
    @Test
    public void testdelete01(){
        Contest contest=new Contest();
        contest.setId(2);
        contestMapper.deleteContest(contest);
        try {
            Thread.sleep(10000);
        }catch (Exception ex){}
        contestMapper.deleteContestByTime(userMapper.getUserById(1),LocalDate.of(2021,05,22));
    }

    @Test
    public void testselect01(){
        Contest contest=contestMapper.getContestById(1);
        System.out.println(JSON.toJSON(contest));
    }

    @Test
    public void testUpdate01(){
        Contest contest=contestMapper.getContestById(7);
        contest.setDescription("一个虚拟的比赛");
        contestMapper.updateContest(contest);
        System.out.println(JSON.toJSON(contest));
    }
}
