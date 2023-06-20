package com.basinda.controllers;

import com.basinda.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public ResponseEntity<HttpStatus> registrationVerify(@Param("code") String code){
        if (userService.verify(code)){
            return ResponseEntity.ok(null);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}