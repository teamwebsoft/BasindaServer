package com.basinda.services;

import com.basinda.models.entity.User;
import jakarta.mail.MessagingException;
import com.basinda.models.request.common.LoginRequest;
import com.basinda.models.request.admin.ApproveRequest;
import com.basinda.models.request.user.RegistrationRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {
    String login(LoginRequest request, final HttpServletResponse res);
    User registerUser(RegistrationRequest request, String applicationUrl);
    boolean verify(String verificationCode);
    boolean approveUser(ApproveRequest request, String applicationUrl) throws MessagingException, UnsupportedEncodingException;
    List<User> allUsers();
}