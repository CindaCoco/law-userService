package com.cinda.user.service;

import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    String getRoleNameById(Integer id);

    Integer getIdByName(String name);
}
