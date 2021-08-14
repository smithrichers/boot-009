package com.example.boot009.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CreateTableReq {
    private String tableName;
    private List<Map<String,Object>> list;



}
