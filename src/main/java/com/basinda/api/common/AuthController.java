package com.basinda.api.common;

import com.basinda.models.entity.User;
import com.basinda.utils.EmailUtils;
import com.basinda.models.eUserType;
import com.basinda.services.UserService;
import com.basinda.models.request.common.LoginRequest;
import com.basinda.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import com.basinda.models.response.ResponseHeader;
import com.basinda.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletResponse;
import com.basinda.models.request.user.RegistrationRequest;
import com.basinda.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public class Response extends ResponseHeader{
        public eUserType userType;
    }

    public class LoginResponse extends ResponseHeader{
        public String token;
        public eUserType userType;
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
            String applicationUrl = EmailUtils.getApplicationUrl(request);
            User createdUser = userService.registerUser(model, applicationUrl);
            if (createdUser==null){
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.setStatus(true);
                response.setContent("Something went wrong please try again.");
            }
            else {
                response.setStatusCode(HttpStatus.CREATED);
                response.setStatus(true);
                response.userType = createdUser.getUserType();
                response.setContent("User created successfully please before login verify your email.");
            }
        }
        return ResponseEntity.status(response.statusCode).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, final HttpServletResponse res) throws ResourceNotFoundException {
        LoginResponse response = new LoginResponse();
        String userLogin = userService.login(request, res);
        if (userLogin.equalsIgnoreCase("valid")){
            List<User> user = userRepository.findByMobileNumber(request.getMobileNumber());
            response.userType = user.get(0).getUserType();
            response.setContent("User Login Successfully.");
            String token = jwtTokenUtil.generateToken();
            response.token = token;
        } else if (userLogin.equalsIgnoreCase("Redirect")) {
            
        } else{
            throw new ResourceNotFoundException("Username or Password incorrect.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}