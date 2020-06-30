package com.cinda.user.domain.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("Role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    Integer id;
    String roleName;
    String permissions;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;
    Boolean isDeleted;
}
