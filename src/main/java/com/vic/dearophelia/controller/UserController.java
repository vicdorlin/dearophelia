package com.vic.dearophelia.controller;

import com.vic.dearophelia.domain.User;
import com.vic.dearophelia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author vicdor
 * @create 2018-02-11 14:59
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired //可以不写
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("person/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        if (userRepository.save(user)) {
            System.out.printf("用户对象：%s保存成功！\n", user);
        }
        return user;
    }

    @GetMapping("persons")
    public Collection<User> list() {
        return userRepository.findAll();
    }
}
