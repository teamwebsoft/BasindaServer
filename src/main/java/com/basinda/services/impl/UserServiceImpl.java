package com.basinda.services.impl;

import com.basinda.entities.User;
import com.basinda.services.UserService;
import org.springframework.stereotype.Service;
import com.basinda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}