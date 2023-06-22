package com.basinda.controllers;

import com.basinda.entities.User;
import com.basinda.utils.EmailUtil;
import com.basinda.services.UserService;
import com.basinda.requests.LoginRequest;
import org.springframework.http.HttpStatus;
import com.basinda.responses.ResponseHeader;
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

    public class Response extends ResponseHeader{

    }

    @PostMapping("/register")
    public ResponseEntity<Response> createUser(@RequestBody RegistrationRequest model, final HttpServletRequest request){
        Response response = new Response();

        if (!model.getPassword().equals(model.getConfirmPassword())){
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setStatus(true);
            response.setContent("Password does not match. Please try again.");
        }
        else{
            String applicationUrl = EmailUtil.getApplicationUrl(request);
            User createdUser = userService.registerUser(model, applicationUrl);
            if (createdUser==null){
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setStatus(true);
                response.setContent("Something went wrong please try again.");
            }
            else {
                response.setStatusCode(HttpStatus.CREATED);
                response.setStatus(true);
                response.setContent("User created successfully please before login verify your email.");
            }
        }
        return ResponseEntity.status(response.statusCode).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest request){
        Response response = new Response();
        String userLogin = userService.login(request);
        if (userLogin.equalsIgnoreCase("valid")){
            response.setContent("User Login Successfully.");
        }
        else{
            response.setStatusCode(HttpStatus.NO_CONTENT);
            response.setContent("Username or Password incorrect.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}