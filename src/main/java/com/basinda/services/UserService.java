package com.basinda.services;

import com.basinda.entities.User;
import com.basinda.requests.RegistrationRequest;

public interface UserService {
    User registerUser(RegistrationRequest request);
}