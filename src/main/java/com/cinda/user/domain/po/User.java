package com.cinda.user.domain.po;

import com.cinda.user.controller.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Alias("User")
public class User {


    private Integer id;
    /**
     * 用户的角色
     */
    private Integer role;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户性别
     */
    private Boolean isMan;
    /**
     * 用户生日
     */
    private LocalDateTime birthday;
    /**
     * 用户电话
     */
    private String mobile;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户注册时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 用户上次修改信息时间
     */
    private LocalDateTime gmtModified;
    /**
     * 用户是否被删除
     */
    private Integer isDeleted;

}
