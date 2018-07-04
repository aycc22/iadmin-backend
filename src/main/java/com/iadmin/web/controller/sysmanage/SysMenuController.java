package com.iadmin.web.controller.sysmanage;

import com.alibaba.fastjson.JSONObject;
import com.iadmin.mybatis.domain.SysPermission;
import com.iadmin.mybatis.mapper.SysPermissionMapper;
import com.iadmin.util.IAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sysMenu")
public class SysMenuController {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @GetMapping("/getSysMenus")
    public JSONObject getSysMenus() {
        List<SysPermission> sysPermissionList =  sysPermissionMapper.selectSysMenus();
        return IAUtil.successJson(sysPermissionList);
    }

    @GetMapping("/getPermissionsByMenuCode")
    public JSONObject getPermissionsByMenuCode(String menuCode) {
        List<SysPermission> sysPermissionList =  sysPermissionMapper.selectPermissionsByMenuCode(menuCode);
        return IAUtil.successJson(sysPermissionList);
    }
}
