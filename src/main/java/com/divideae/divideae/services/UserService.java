package com.divideae.divideae.services;

import com.divideae.divideae.domain.user.User;
import com.divideae.divideae.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public User findUser(String id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
}
