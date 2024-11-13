package com.divideae.divideae.controllers;

import com.divideae.divideae.domain.user.User;
import com.divideae.divideae.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> findUsers(){
        System.out.println("entrou findUsers()");
        List<User> users = userService.findUsers();
        return users;
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") String id){
        return userService.findUser(id);
    }
}
