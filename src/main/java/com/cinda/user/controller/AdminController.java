package com.cinda.user.controller;

import com.alibaba.fastjson.JSON;
import com.cinda.user.controller.vo.UserVO;
import com.cinda.user.domain.po.Role;
import com.cinda.user.domain.po.User;
import com.cinda.user.service.RoleService;
import com.cinda.user.service.UserService;
import com.cinda.user.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class AdminController {

    @Resource
    UserService userServiceImpl;

    @Resource
    RoleService roleServiceImpl;

    @PutMapping("/admin/users")
    public Object updateUser(@RequestBody User user, HttpServletRequest request){

        boolean allowableAdmin = validate(request, "管理员");
        boolean allowableGov = validate(request, "政府工作人员");
        if(!allowableAdmin && !allowableGov){
            return ResponseUtil.customization(701,"用户无权限");
        }

        Boolean success = userServiceImpl.updateUser(user);
        if(success) {
            return ResponseUtil.customization(200, "修改成功");
        }
        return ResponseUtil.customization(501, "修改失败");
    }


    @DeleteMapping("/admin/users")
    public Object deleteUser(@RequestParam Integer id, HttpServletRequest request){
        boolean allowableAdmin = validate(request, "管理员");
        if(!allowableAdmin){
            return ResponseUtil.customization(701,"用户无权限");
        }

        Boolean success = userServiceImpl.deleteUser(id);
        if(success) {
            return ResponseUtil.customization(200, "删除成功");
        }
        return ResponseUtil.customization(502, "删除失败");
    }

    @GetMapping("/admin/users")
    public Object getUserList(HttpServletRequest request){
        boolean allowableAdmin = validate(request, "管理员");
        if(!allowableAdmin){
            return ResponseUtil.customization(701,"用户无权限");
        }

        List<UserVO> userVOList = userServiceImpl.getUserList();
        return ResponseUtil.customization(200, "返回用户列表成功", userVOList);
    }

    @GetMapping("admin/roles")
    public Object getRoleList(HttpServletRequest request){
        boolean allowableAdmin = validate(request, "管理员");
        if(!allowableAdmin){
            return ResponseUtil.customization(701, "用户无权限");
        }
        List<Role> roleList = roleServiceImpl.getRoles();
        return ResponseUtil.customization(200, "返回角色列表成功", roleList);
    }

    private boolean validate(HttpServletRequest request, String requireRole){
        String currentUserString = request.getHeader("user");
        log.info("当前用户："+currentUserString);
        User currentUser = JSON.parseObject(currentUserString,User.class);
        String role = roleServiceImpl.getRoleNameById(currentUser.getRole());
        log.info("当前登录用户角色为"+role);
        return role.equals(requireRole);
    }
}
