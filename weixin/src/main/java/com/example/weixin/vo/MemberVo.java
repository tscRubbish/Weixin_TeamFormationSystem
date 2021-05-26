package com.example.weixin.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Getter
@Setter
public class MemberVo {
    private UserVo userVo;
    private TeamVo teamVo;
}
