package com.basinda.controllers;

import com.basinda.entities.User;
import com.basinda.utils.EmailUtil;
import com.basinda.services.UserService;
import com.basinda.requests.LoginRequest;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.basinda.requests.RegistrationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody RegistrationRequest model, final HttpServletRequest request){
        if (!model.getPassword().equals(model.getConfirmPassword())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        String applicationUrl = EmailUtil.getApplicationUrl(request);
        User createdUser = userService.registerUser(model, applicationUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginRequest request){
        String response = userService.login(request);
        if (response.equalsIgnoreCase("valid")){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}