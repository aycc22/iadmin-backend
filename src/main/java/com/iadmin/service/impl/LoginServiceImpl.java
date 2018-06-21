package com.iadmin.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.iadmin.mybatis.mapper.SysUserMapper;
import com.iadmin.service.LoginService;
import com.iadmin.service.PermissionService;
import com.iadmin.util.Constants.ErrorEnum;
import com.iadmin.util.IAUtil;
import com.iadmin.util.Constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @author: hxy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionService permissionService;
    /**
     * 登录表单提交
     * 登录时调用{@link com.iadmin.config.shiro.ShiroRealm } 中的 AuthenticationInfo方法进行登陆认证
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject authLogin(JSONObject jsonObject) {

        String username = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        JSONObject returnData = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 这个方法会调用 ShiroRealm 中的 AuthenticationInfo方法进行登陆认证
            currentUser.login(token);
            returnData.put("result", "success");
            returnData.put("token", token);
        } catch (AuthenticationException e) {
            return IAUtil.errorJson(ErrorEnum.E_2004);
        }
        return IAUtil.successJson(returnData);
    }

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public JSONObject getUser(String username, String password) {
        return sysUserMapper.getUser(username, password);
    }

    /**
     * 查询当前登录用户的权限等信息
     *
     * @return
     */
    @Override
    public JSONObject getInfo() {
        //从session获取用户信息
        System.out.println("getInfo从session获取用户信息#####" + SecurityUtils.getSubject().getSession().getId());
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("userName");
        JSONObject returnData = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        returnData.put("userPermission", userPermission);
        /*name: 'super_admin',
                user_id: '1',
                access: ['super_admin', 'admin'],
        token: 'super_admin',
                avator: 'https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png'*/
        List access = Arrays.asList("super_admin", "admin");
        returnData.put("name", "super_admin");
        returnData.put("user_id", "1");
        returnData.put("access", access);
        returnData.put("avator", "https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
        return IAUtil.successJson(returnData);

    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public JSONObject logout() {
        logger.info("退出登录" + SecurityUtils.getSubject().getPrincipal());
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IAUtil.successJson();
    }
}
