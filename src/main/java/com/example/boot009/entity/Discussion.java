package com.example.boot009.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Discussion {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String article;
    private String time;
}
