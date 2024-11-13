package com.divideae.divideae.controllers;

import com.divideae.divideae.domain.user.AuthenticationDTO;
import com.divideae.divideae.domain.user.LoginResponseDTO;
import com.divideae.divideae.domain.user.RegisterDTO;
import com.divideae.divideae.domain.user.User;
import com.divideae.divideae.infra.security.TokenService;
import com.divideae.divideae.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("divideae")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        try{
            var emailPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(emailPassword);

            var token =  tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch(Exception e){
            System.out.println("Erro ao efetuar login");
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.CPF(), data.nome(), data.datanascimento(), data.chavepix());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
