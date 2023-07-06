package com.basinda.api.user;

import com.basinda.models.response.ResponseHeader;
import com.basinda.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public class Response extends ResponseHeader{

    }

    @GetMapping("/verify")
    public ResponseEntity<Response> registrationVerify(@RequestParam("code") String code){
        Response response = new Response();

        if (userService.verify(code)){
            response.setStatusCode(HttpStatus.OK);
            response.setStatus(true);
            response.setContent("User verified successfully.");
        }
        else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}