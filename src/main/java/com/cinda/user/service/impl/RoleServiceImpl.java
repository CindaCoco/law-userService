package com.cinda.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.cinda.user.domain.po.Role;
import com.cinda.user.mapper.PermissionMapper;
import com.cinda.user.mapper.RoleMapper;
import com.cinda.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Resource
    PermissionMapper permissionMapper;
    @Override
    public String getRoleNameById(Integer id) {
        return roleMapper.findRoleById(id).getRoleName();
    }

    @Override
    public Integer getIdByName(String name) {
        return roleMapper.findIdByRole(name);
    }

    @Override
    public List<String> getPermissionIds(Integer id) {
        String ids = roleMapper.getPermissionIds(id);
        List<Integer> list = JSON.parseArray(ids, Integer.class);
        List<String> result = new ArrayList<>();
        for(Integer roleId:list){
            result.add(permissionMapper.getDescriptionById(roleId));
        }
        return result;
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoleList();
    }
}
