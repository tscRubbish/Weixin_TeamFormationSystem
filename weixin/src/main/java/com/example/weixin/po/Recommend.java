package com.example.weixin.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Getter
@Setter
public class Recommend {
    private Integer contestId;
    private  String pic;
}
