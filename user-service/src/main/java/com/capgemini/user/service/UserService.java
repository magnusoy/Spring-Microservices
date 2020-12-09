package com.capgemini.user.service;


import com.capgemini.user.VO.Department;
import com.capgemini.user.VO.ResponseTemplateVO;
import com.capgemini.user.entity.User;
import com.capgemini.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService");
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        log.info("Inside findUserById method of UserService");
        return userRepository.findByUserId(userId);
    }

    public void deleteUserById(Long userId) {
        log.info("Inside deleteUserById method of UserService");
        userRepository.deleteById(userId);
    }

    public ResponseTemplateVO updateUser(User user) {
        log.info("Inside updateUser method of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User updatedUser = userRepository.save(user);
        Department department =
                restTemplate.getForObject(
                        "http://DEPARTMENT-SERVICE/departments/" + updatedUser.getDepartmentId(),
                        Department.class);
        vo.setUser(updatedUser);
        vo.setDepartment(department);
        return vo;
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment method of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department =
                restTemplate.getForObject(
                        "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                        Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
