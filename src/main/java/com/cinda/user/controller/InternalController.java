package com.cinda.user.controller;

import com.alibaba.fastjson.JSON;
import com.cinda.user.domain.po.User;
import com.cinda.user.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class InternalController {


    @Resource
    RoleService roleServiceImpl;

    /**
     * 内部接口验证权限
     * @param user 当前登录用户信息
     * @param requireRole 需要的角色
     * @return 是否是该角色
     */
    @GetMapping("/validate")
    public boolean validate(@RequestParam String user,@RequestParam String requireRole){
        try{
            User currentUser = JSON.parseObject(user, User.class) ;
            String role = roleServiceImpl.getRoleNameById(currentUser.getRole());
            return role.equals(requireRole);
        }catch (Exception e){
            return false;
        }
    }
}
