package com.example.stack.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.stack.service.ReflectAsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ReflectAsmApi {

    @Autowired
    private ReflectAsmService reflectAsmService;

    @GetMapping(value = "/test")
    public JSONObject test(){
        try {
            reflectAsmService.testMothod();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
}
