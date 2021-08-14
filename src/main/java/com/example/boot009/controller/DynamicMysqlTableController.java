package com.example.boot009.controller;

import com.example.boot009.entity.CreateTableReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/table")
@RestController
@Api(tags = "数据库表相关")
public class DynamicMysqlTableController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * execute方法用来直接操作sql语句
     */
    @ApiOperation("创建数据库的表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName",value = "用户传来的表名，不为数字"),
            @ApiImplicitParam(name = "fieldName",value = "用户传来的列名，不为数字"),
            @ApiImplicitParam(name = "fieldType",value = "用户传来的列的种类"),
            @ApiImplicitParam(name = "fieldExtent",value = "用户传来的列的种类的长度")
    })
    @PostMapping("/createTable")
    public String createTable(@RequestBody CreateTableReq req){
        String tableName = req.getTableName();
        StringBuilder stringBuilder = new StringBuilder( "create table "+tableName+"(id int(11) primary key,");
        List<Map<String, Object>> list = req.getList();
        for (int i=0;i<list.size();i++){
            stringBuilder.append(list.get(i).get("fieldName"));
            stringBuilder.append(" ");
            stringBuilder.append(list.get(i).get("fieldType"));
            stringBuilder.append("(");
            stringBuilder.append(list.get(i).get("fieldExtent"));
            stringBuilder.append(")");
            if (list.size()-1!=i){
                stringBuilder.append(",");
            }
            if (list.size()-1==i){
                stringBuilder.append(")");
            }
        }
        log.info("拼接后的sql语句：{}",stringBuilder);
        jdbcTemplate.execute(stringBuilder.toString());
        return "创建表成功";
    }
    @ApiOperation("展示表名")
    @GetMapping("/listTable")
    public  List<Map<String, Object>> listAllTable(){
        String[] a;
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select table_name from information_schema.tables where table_schema='library' and table_type='base table'");
        for(Map<String,Object> map:list){
            System.out.println(map);
        }
        return  list;
    }
}