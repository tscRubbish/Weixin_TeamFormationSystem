package com.example.weixin.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ContestForm {
    private Integer id;
    private String name;
    private UserVo sponsor;
    private LocalDate startTime;
    private LocalDate endTime;
}
