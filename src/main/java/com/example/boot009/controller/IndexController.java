package com.example.boot009.controller;

import com.example.boot009.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){

        return "hello";
    }
    @RequestMapping("/test")
    public String test(ModelMap map){
        User u = new User();
        u.setUsername("haozz");
        u.setPassword("qwerty");

        User u1 = new User();
        u1.setUsername("nico robin");
        u1.setPassword("qwerty");

        User u2 = new User();
        u2.setUsername("nami");
        u2.setPassword("qwerty");

        List<User> userList = new ArrayList<>();
        userList.add(u);
        userList.add(u1);
        userList.add(u2);
        map.addAttribute("user",u);
        map.addAttribute("userList",userList);
        return "hello";
    }

    @PostMapping("/postform")
    public String postform(User u){
        System.out.println(u.getUsername());
        return "redirect:/test";
    }


}
