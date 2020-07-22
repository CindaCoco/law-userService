package com.cinda.user.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    String getRoleNameById(Integer id);

    Integer getIdByName(String name);

    List<String> getPermissionIds(Integer id);
}
