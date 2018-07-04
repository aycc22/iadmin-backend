package com.iadmin.web.controller.sysmanage;


import com.alibaba.fastjson.JSONObject;
import com.iadmin.mybatis.domain.SysRole;
import com.iadmin.mybatis.domain.SysUser;
import com.iadmin.mybatis.mapper.SysRoleMapper;
import com.iadmin.mybatis.mapper.SysUserMapper;
import com.iadmin.util.IAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @RequestMapping("getSysUser")
    public JSONObject getSysUser() {
        List<SysUser> sysUserList = sysUserMapper.selectListBySQL();
        JSONObject jsonObject = IAUtil.successJson(sysUserList);
        return jsonObject;
    }

    @GetMapping("getRolesByUsername")
    public JSONObject getRolesByUsername(String username){
        List<SysRole> sysRoles = sysRoleMapper.selectRoleByUsername(username);
        return IAUtil.successJson(sysRoles);
    }
}
