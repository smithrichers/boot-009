package com.example.boot009.controller;

import com.example.boot009.entity.Discussion;
import com.example.boot009.mapper.DiscussionMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@Api(tags = "处理讨论数据")
@RestController
public class DiscussionController {
    @Resource
    private DiscussionMapper discussionMapper;
    @ApiOperation("列出全部的讨论数据")
    @ApiImplicitParam(name = "",value = "List<Discussion>")
    @GetMapping("/DiscussionAll")
    public List<Discussion> listAll() {
        List<Discussion> list = discussionMapper.selectList(null);
        return list;
    }
    @ApiOperation("插入讨论信息")
    @ApiImplicitParam(name = "Discussion",value = "前端传来的Discussion")
    @PostMapping("/DiscussionSave")
    public void addDiscussion(@RequestBody Discussion discussion) {
        discussionMapper.insert(discussion);
    }
}
