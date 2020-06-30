package com.cinda.user.mapper;

import com.cinda.user.domain.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface RoleMapper {
    Role findRoleById(Integer id);

    Boolean addRole(Role role);

    Boolean updateRole(Role role);

    Boolean deleteRoleById(Integer id);

    List<Role> getRoleList();

    Integer findIdByRole(String role);
}
