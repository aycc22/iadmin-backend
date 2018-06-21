package com.iadmin.web.controller;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iadmin.mybatis.domain.SysUser;
import com.iadmin.mybatis.mapper.SysUserMapper;
import com.iadmin.util.IAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping("getSysUser")
    public JSONObject getSysUser() {
        List<SysUser> sysUserList = sysUserMapper.selectListBySQL();
        JSONArray jsonArray=  (JSONArray)JSONArray.toJSON(sysUserList);
        System.out.println(jsonArray.toString());
        JSONObject jsonObject = IAUtil.successJson(sysUserList);
        return jsonObject;
    }
}
