package com.basinda.controllers;

import com.basinda.responses.ResponseHeader;
import com.basinda.services.UserService;
import com.basinda.requests.ApproveRequest;
import com.basinda.utils.EmailUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    public class Response extends ResponseHeader{

    }

    @PostMapping("/approve")
    public ResponseEntity<Response> approved(@RequestBody ApproveRequest request, final HttpServletRequest servletRequest){
        Response response = new Response();
        String applicationUrl = EmailUtil.getApplicationUrl(servletRequest);

        try {
            boolean isregistered = userService.approveUser(request,applicationUrl);
            if (isregistered){
                response.setStatusCode(HttpStatus.OK);
                response.setStatus(true);
                response.setContent("User approved successfully.");
            }
            else{
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setStatus(true);
                response.setContent("Something went wrong please try again.");
            }
        }
        catch (Exception ex){
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/reject")
    public ResponseEntity<HttpStatus> reject(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}