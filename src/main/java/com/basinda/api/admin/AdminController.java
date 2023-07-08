package com.basinda.api.admin;

import com.basinda.models.entity.User;
import com.basinda.utils.EmailUtils;
import com.basinda.services.UserService;
import org.springframework.http.HttpStatus;
import com.basinda.models.request.admin.ApproveRequest;
import com.basinda.models.response.ResponseHeader;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    public class Response extends ResponseHeader{

    }

    @PostMapping("/allUsers")
    public ResponseEntity<List<User>> allUsers(){
        List<User> users = userService.allUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/approve")
    public ResponseEntity<Response> approved(@RequestBody ApproveRequest request, final HttpServletRequest servletRequest){
        Response response = new Response();
        String applicationUrl = EmailUtils.getApplicationUrl(servletRequest);

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