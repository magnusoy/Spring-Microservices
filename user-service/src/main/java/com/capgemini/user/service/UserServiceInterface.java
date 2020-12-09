package com.capgemini.user.service;

import com.capgemini.user.VO.ResponseTemplateVO;
import com.capgemini.user.entity.User;

public interface UserServiceInterface {

    User saveUser(User user);

    User findUserById(Long userId);

    void deleteUserById(Long userId);

    ResponseTemplateVO updateUser(User user);
}
