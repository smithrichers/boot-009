package com.example.boot009.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxj
 * @version 2021/8/11 0013
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEmailEntity {

    /**
     * 主题
     */
    private String subject;

    /**
     * 主题内容
     */
    private String content;

    /**
     * 接收人邮箱列表
     */
    private String tos;

}
