package com.cinda.user.service;

import com.cinda.user.controller.vo.UserVO;
import com.cinda.user.domain.po.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserByName(String username);

    Boolean isExist(String username);

    Object register(User user);

    User voToUser(UserVO userVO);

    UserVO userToVo(User user);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(Integer id);

    List<UserVO> getUserList();
}
