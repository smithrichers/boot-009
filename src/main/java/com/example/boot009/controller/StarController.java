package com.example.boot009.controller;

import com.example.boot009.entity.Star;
import com.example.boot009.mapper.StarMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@Api(tags = "展示全部的星愿")
@RestController
public class StarController {
    @Resource
    private StarMapper starMapper;
    @ApiOperation("列出全部的星愿数据")
    @ApiImplicitParam(name = "",value = "List<Star>")
    @GetMapping("/StarAll")
    public List<Star> listAll() {
        List<Star> list = starMapper.selectList(null);
        return list;
    }
}
