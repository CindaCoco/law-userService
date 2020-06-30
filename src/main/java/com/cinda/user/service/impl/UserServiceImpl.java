package com.cinda.user.service.impl;

import com.cinda.user.controller.vo.UserVO;
import com.cinda.user.mapper.RoleMapper;
import com.cinda.user.mapper.UserMapper;
import com.cinda.user.domain.po.User;
import com.cinda.user.service.UserService;
import com.cinda.user.util.ResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public Boolean isExist(String username) {
        User user=userMapper.findByName(username);
        return user != null;
    }

    @Override
    public Object register(User user) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Boolean isExist=isExist(username);
        if(isExist){
            return ResponseUtil.customization(610, "用户已存在");
        }
        user.setUsername(username);
        Boolean success = userMapper.addUser(user);
        if(success){
            return ResponseUtil.customization(200, "注册成功");
        }
        return ResponseUtil.customization(611, "注册失败");
    }

    @Override
    public User voToUser(UserVO userVO) {
        User user = new User();
        Integer roleId = roleMapper.findIdByRole(userVO.getRole());
        user.setRole(roleId);
        user.setPassword(userVO.getPassword());
        user.setUsername(userVO.getUsername());
        user.setAvatar(userVO.getAvatar());
        user.setIsMan(userVO.getIsMan());
        user.setBirthday(userVO.getBirthday());
        user.setMobile(userVO.getMobile());
        user.setEmail(userVO.getEmail());
        return user;
    }

    @Override
    public UserVO userToVo(User user) {
        UserVO userVO = new UserVO();
        String role = roleMapper.findRoleById(user.getRole()).getRoleName();
        userVO.setRole(role);
        userVO.setPassword(user.getPassword());
        userVO.setUsername(user.getUsername());
        userVO.setAvatar(user.getAvatar());
        userVO.setIsMan(user.getIsMan());
        userVO.setBirthday(user.getBirthday());
        userVO.setMobile(user.getMobile());
        userVO.setEmail(user.getEmail());
        return userVO;
    }

    @Override
    public Boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public List<UserVO> getUserList() {
        List<User> userList = userMapper.getUserList();
        List<UserVO> userVOList = new ArrayList<>();
        for(User user: userList){
            UserVO userVO=userToVo(user);
            userVOList.add(userVO);
        }
        return userVOList;
    }

}
