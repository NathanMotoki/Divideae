package com.divideae.divideae.controllers;

import com.divideae.divideae.domain.user.AuthenticationDTO;
import com.divideae.divideae.domain.user.LoginResponseDTO;
import com.divideae.divideae.domain.user.RegisterDTO;
import com.divideae.divideae.domain.user.User;
import com.divideae.divideae.infra.security.TokenService;
import com.divideae.divideae.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
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

            var user = (User) auth.getPrincipal();

            /*var response = Map.of(
                    "id", user.getId(),
                    "email", user.getLogin(),
                    "dtNascimento", user.getDatanascimento(),
                    "pix", user.getChavepix(),
                    "token", token
            );*/

            LoginResponseDTO response = new LoginResponseDTO(
                    user.getId(),
                    user.getLogin(),
                    user.getNome(),
                    user.getChavepix(),
                    user.getDatanascimento(),
                    user.isProfileComplete(),
                    token
            );

            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("message", "Erro ao efetuar login, tente novamente"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        try{
            if(this.repository.findByLogin(data.login()) != null)  return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Email já cadastrado"));

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.login(), encryptedPassword);
            this.repository.save(newUser);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(Map.of("message", "Erro ao efetuar cadastro: " + e));
        }

    }
}
