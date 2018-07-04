package com.iadmin.web.controller.sysmanage;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.iadmin.mybatis.domain.SysRole;
import com.iadmin.mybatis.mapper.SysRoleMapper;
import com.iadmin.util.IAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @PostMapping("/getSysRoles")
    public JSONObject getSysRoles() {
        PageHelper.startPage(1, 3);
        List<SysRole> sysRoles= sysRoleMapper.selectAllRole();
        return IAUtil.successJson(sysRoles);
    }
}
