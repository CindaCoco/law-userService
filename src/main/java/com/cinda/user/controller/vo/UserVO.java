package com.cinda.user.controller.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO {
    private Integer id;
    private String role;
    private String username;
    private String password;
    private String avatar;
    private Boolean isMan;
    private LocalDateTime birthday;
    private String mobile;
    private String email;
}
