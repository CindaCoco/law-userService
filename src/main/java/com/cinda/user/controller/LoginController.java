package com.cinda.user.controller;

import com.cinda.user.controller.vo.LoginVO;
import com.cinda.user.domain.po.User;
import com.cinda.user.service.UserService;
import com.cinda.user.util.JWTUtil;
import com.cinda.user.util.RedisUtil;
import com.cinda.user.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Value("${secret.key}")
    String secretKey;

    @Resource
    RedisUtil redisUtil;

    @Resource
    UserService userServiceImpl;

    @PostMapping("/login")
    public Object login(@RequestBody LoginVO loginVO) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = loginVO.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User userPO = userServiceImpl.getUserByName(username);
        if (userPO == null) {
            return ResponseUtil.customization(601, "用户不存在");
        }
        if (!loginVO.getPassword().equals(userPO.getPassword())) {
            return ResponseUtil.customization(602, "密码错误");
        }
        Integer roleId = userPO.getRole();
        if (roleId == 2) {
            String token = JWTUtil.generateToken("管理员", secretKey);
            redisUtil.set(token, userPO, 30);
            return ResponseUtil.customization(201, "管理员登录成功", token);
        } else if (roleId == 3) {
            String token = JWTUtil.generateToken("法务工作者", secretKey);
            redisUtil.set(token, userPO, 30);
            return ResponseUtil.customization(202, "法务工作者登录成功", token);
        } else if (roleId == 4) {
            String token = JWTUtil.generateToken("政府工作人员", secretKey);
            redisUtil.set(token, userPO, 30);
            return ResponseUtil.customization(203, "政府工作人员登录成功", token);
        } else if (roleId == 1) {
            String token = JWTUtil.generateToken("普通用户", secretKey);
            redisUtil.set(token, userPO, 30);
            return ResponseUtil.customization(200, "普通用户登录成功", token);
        } else {
            return ResponseUtil.customization(-1, "未定义用户角色，不授予token");
        }
    }

    @PostMapping("/register")
    public Object register(@RequestBody User requestUser){
        return userServiceImpl.register(requestUser);
    }

}
