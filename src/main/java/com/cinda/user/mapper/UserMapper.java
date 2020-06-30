package com.cinda.user.mapper;

import com.cinda.user.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return user
     */
    User findByName(String username);

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return user
     */
    User findById(Integer id);

    /**
     * 注册新用户
     * @param user 用户
     * @return 添加结果
     */
    Boolean addUser(User user);

    /**
     * 修改用户信息
     * @param user 用户
     * @return 修改结果
     */
    Boolean updateUser(User user);

    /**
     * 根据userId删除用户
     * @param id 用户id
     * @return 删除结果
     */
    Boolean deleteUser(Integer id);

    List<User> getUserList();
}
