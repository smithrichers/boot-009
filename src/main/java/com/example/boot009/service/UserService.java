package com.example.boot009.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot009.entity.User;

import java.util.List;


public interface UserService extends IService<User> {

    /**
     * 查询所有
     *
     * @return
     */
    List<User> listAll();

    /**
     * id条件查询
     *
     * @return
     */
    User listOne(Long id);

    /***
     * 保存
     * @param user
     * @return
     */
    int insert1(User user);

}