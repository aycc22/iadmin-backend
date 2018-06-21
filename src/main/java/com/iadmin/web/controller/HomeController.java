package com.iadmin.web.controller;



import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/home1")
public class HomeController{

    @RequestMapping("index")
    @ResponseBody
    @RequiresPermissions("home:list")
    public String home() {
        return "home index msg";
    }

    @ResponseBody
    @RequestMapping("unauthz")
    public String unauthz(){
        System.out.println("unauthzunauthzunauthz++++++==========================");
        return "unauthz 哈哈";
    }

    @RequestMapping("/auth")
    public JSONObject authLogin(/*@RequestBody JSONObject requestJson*/) {
        //  IAUtil.hasAllRequired(requestJson, "username,password");
        System.out.println("######执行登录######");
        return null/*loginService.authLogin(requestJson)*/;
    }
}
