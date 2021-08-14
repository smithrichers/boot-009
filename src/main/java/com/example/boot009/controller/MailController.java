package com.example.boot009.controller;

import com.example.boot009.entity.SimpleEmailEntity;
import com.example.boot009.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "处理邮件相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private final MailService mailService;
    @ApiOperation("发送邮件")
    @ApiImplicitParam(name = "simpleEmailEntity",value = "前端传来的simpleEmailEntity")
    @PostMapping("/simple")
    public void sendSimpleMail(@RequestBody SimpleEmailEntity simpleEmailEntity) {
        mailService.sendSimpleMail(simpleEmailEntity);
    }
}
