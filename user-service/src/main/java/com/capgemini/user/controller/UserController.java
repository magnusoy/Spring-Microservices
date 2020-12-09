package com.capgemini.user.controller;

import com.capgemini.user.VO.ResponseTemplateVO;
import com.capgemini.user.entity.User;
import com.capgemini.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable("id") Long userId) {
        ResponseTemplateVO responseTemplateVO = userService.getUserWithDepartment(userId);
        if (responseTemplateVO == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(responseTemplateVO);
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController");
        userService.saveUser(user);
        URI uri = URI.create("/users/" + user.getUserId());
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (userService.findUserById(user.getUserId()) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(user);
        URI uri = URI.create("/departments/" + user.getDepartmentId());
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long userId) {
        if (userService.findUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }


}
