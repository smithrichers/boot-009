package com.example.boot009.service.serviceImp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot009.entity.User;
import com.example.boot009.mapper.UserMapper;
import com.example.boot009.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    //查询所有
    @Override
    public List<User> listAll() {
        List<User> users = this.list();
        return users;
    }

    //条件查询
    public User listOne(Long id) {
        User user = this.getById(id);
        return user;
    }

    //保存
    public int insert1(User user) {
        return userMapper.insert(user);

    }

}