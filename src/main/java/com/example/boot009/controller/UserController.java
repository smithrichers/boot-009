package com.example.boot009.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot009.entity.User;
import com.example.boot009.mapper.UserMapper;
import com.example.boot009.util.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "用户管理相关接口")
public class UserController {
    @Resource
    private UserMapper userMapper;

    @ApiOperation("根据username查询用户的接口")
    @ApiImplicitParam(name = "username", value = "用户名称", required = true)
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user) throws JsonProcessingException {
//        String comparison=user.getPassword();
        //实现根据条件查询指定数据
        /**
         *
         * @param :username
         */
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("username", user.getUsername());
//        System.out.println(columnMap);
        List<User> userList = userMapper.selectByMap(columnMap);
//        System.out.println(userList);
        String result = "";
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//将传来的数据加密
        System.out.println(md5Password);
        if (userList.size() == 1) {
            for (User userTest : userList) {
                //将数据库中的密码与传来的数据匹配
                if (userTest.getPassword().equals(md5Password)) {
                    System.out.println(userTest.getPassword().equals(md5Password));
                    //生成token
                    String token = TokenUtil.sign(user);
                    HashMap<String, Object> hs = new HashMap<>();
                    hs.put("token", token);
                    ObjectMapper objectMapper = new ObjectMapper();
                    //将token赋值给result
                    result = objectMapper.writeValueAsString(hs);
                    System.out.println(result);
                    //将token插入数据库
                    userTest.setToken(token);
//                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//                    queryWrapper.eq("id", userTest.getId());//更新的条件
                    //执行更新操作
                    int updateresult = userMapper.updateById(userTest);
//                    System.out.println("result: " + updateresult);
                } else {
                    result = "密码错误";

                }
            }
        } else {
            result = "error";
        }
        return result;
    }

    @ApiOperation("验证token是否有效")
    @ApiImplicitParam(name = "valid", value = "前端传来的token", required = true)
    @PostMapping("/validToken")
    public boolean validToken(@RequestBody String valid) {
        //解析json字符串
        JSONObject json = JSONObject.fromObject(valid);
        String tokenString = json.getString("token");
//        System.out.println(valid);
//        System.out.println(tokenString);
//        System.out.println(isValid);
        return TokenUtil.verify(tokenString);

    }

    @ApiOperation("查询全部用户信息")
    @GetMapping("/listAll")
    public List<User> listAll() {
//        List<User> userList = userService.listAll();
        List<User> list = userMapper.selectList(null);


        return list;

//        return userList;
    }

    @ApiOperation("插入用户信息")
    @ApiImplicitParam(name = "user", value = "前端传来的User")
    @PostMapping("/save")
    public void addUser(@RequestBody User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());//将传来的数据加密
        user.setPassword(password);
        userMapper.insert(user);
//        System.out.println(user);
    }

    @ApiOperation("根据id删除")
    @ApiImplicitParam(name = "id", value = "前端传来的id")
    //根据id删除
    @DeleteMapping("/delete/{id}")
    public String baocun1(@PathVariable("id") Integer id) {
        userMapper.deleteById(id);

        return "success";
    }
}