package com.example.boot009.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Star {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String icon;
    private String name;
    private String article;
    private String sendLabel_1;
    private String sendLabel_2;
    private String sendLabel_3;
    private String sendTime;
}
