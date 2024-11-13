package com.divideae.divideae.controllers;

import com.divideae.divideae.domain.user.User;
import com.divideae.divideae.repositories.UserRepository;
import com.divideae.divideae.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity findAllUsers(){
        var allUsers = repository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("{id}")
    public ResponseEntity findUser(@PathVariable("id") String id){
        var user = repository.findById(id);
        return ResponseEntity.ok(user);
    }
}
