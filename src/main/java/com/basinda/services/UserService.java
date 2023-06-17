package com.basinda.services;

import com.basinda.entities.User;
import com.basinda.requests.LoginRequest;
import com.basinda.requests.RegistrationRequest;

public interface UserService {
    String login(LoginRequest request);
    User registerUser(RegistrationRequest request);
}