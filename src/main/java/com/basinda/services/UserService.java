package com.basinda.services;

import com.basinda.entities.User;
import jakarta.mail.MessagingException;
import com.basinda.requests.LoginRequest;
import com.basinda.requests.ApproveRequest;
import com.basinda.requests.RegistrationRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;

public interface UserService {
    String login(LoginRequest request, final HttpServletResponse res);
    User registerUser(RegistrationRequest request, String applicationUrl);
    boolean verify(String verificationCode);
    boolean approveUser(ApproveRequest request, String applicationUrl) throws MessagingException, UnsupportedEncodingException;
}