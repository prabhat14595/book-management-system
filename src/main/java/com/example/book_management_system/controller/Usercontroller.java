package com.example.book_management_system.controller;

import com.example.book_management_system.model.AuthUser;
import com.example.book_management_system.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Usercontroller {

    private AuthUserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @RequestMapping("/register")
    public ResponseEntity registerUser(@RequestBody AuthUser user){
        try{
            if(userRepository.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is Already Present.");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            AuthUser save = userRepository.save(user);
            return ResponseEntity.ok(HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }


}
