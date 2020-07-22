package com.cinda.user.controller;

import com.alibaba.fastjson.JSON;
import com.cinda.user.domain.po.User;
import com.cinda.user.service.RoleService;
import com.cinda.user.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class RoleController {

    @Resource
    RoleService roleServiceImpl;

    @GetMapping("/role")
    public Object getRole(HttpServletRequest request){
        User user = JSON.parseObject(request.getHeader("user"), User.class);
        Integer roleId = user.getRole();
        return ResponseUtil.ok(roleServiceImpl.getPermissionIds(roleId));
    }
}
