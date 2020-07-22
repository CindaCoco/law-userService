package com.cinda.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PermissionMapper {

    public String getDescriptionById(Integer id);
}
