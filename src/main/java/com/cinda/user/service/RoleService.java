package com.cinda.user.service;

import com.cinda.user.domain.po.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    String getRoleNameById(Integer id);

    Integer getIdByName(String name);

    List<String> getPermissionIds(Integer id);

    List<Role> getRoles();
}
