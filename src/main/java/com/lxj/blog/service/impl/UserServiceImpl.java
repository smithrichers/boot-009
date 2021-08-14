package com.lxj.blog.service.impl;

import com.lxj.blog.entity.User;
import com.lxj.blog.mapper.UserMapper;
import com.lxj.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 刘兴俊
 * @since 2021-08-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
