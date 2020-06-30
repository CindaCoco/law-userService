package com.cinda.user.service.impl;

import com.cinda.user.mapper.RoleMapper;
import com.cinda.user.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public String getRoleNameById(Integer id) {
        return roleMapper.findRoleById(id).getRoleName();
    }

    @Override
    public Integer getIdByName(String name) {
        return roleMapper.findIdByRole(name);
    }
}
