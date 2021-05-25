package com.example.weixin;

import com.alibaba.fastjson.JSON;
import com.example.weixin.data.ContestMapper;
import com.example.weixin.data.TeamMapper;
import com.example.weixin.po.Contest;
import com.example.weixin.po.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TeamDAOTest {

    @Autowired
    TeamMapper teamMapper;

    @Test
    public void createTest01(){
        Team team=new Team();
        team.setName("12345");
        team.setPassword("123456789");
        team.setCaptainId(2);
        team.setContestId(7);
        teamMapper.createTeam(team);
        System.out.println(team.getId());
    }
    @Test
    public void getByIdTest01(){
        Team team=teamMapper.getTeamById(3);
        System.out.println(JSON.toJSON(team));
    }
    @Test
    public void getByWordTest01(){
        Team team=teamMapper.getTeamByword("回收").get(0);
        System.out.println(JSON.toJSON(team));
    }
    @Test
    public void addMembersTest01(){
        Team team=teamMapper.getTeamById(1);
        teamMapper.addMember(team,6);
        teamMapper.addMember(team,8);
    }
    @Test
    public void deleteAndShowTest01(){
        Team team=teamMapper.getTeamById(1);
        System.out.println(JSON.toJSON(teamMapper.getMembers(team)));
        teamMapper.deleteMember(team,8);
        System.out.println(JSON.toJSON(teamMapper.getMembers(team)));
    }
    @Test
    public void updateTest01(){
        Team team=teamMapper.getTeamById(2);
        team.setDescription("垃圾回收中心");
        team.setCaptainNotice("114514tql2333");
        teamMapper.updateTeam(team);
        System.out.println(JSON.toJSON(team));
    }
}
