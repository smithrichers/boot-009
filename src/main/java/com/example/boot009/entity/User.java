package com.example.boot009.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    //设置主键自增，不用写id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String token;
    private String permission;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    @Version
    private Integer version;

//    注解实现逻辑删除
    @TableLogic
    private Integer deleted;
}
