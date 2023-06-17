package com.basinda.services.impl;

import com.basinda.config.UserLoadService;
import com.basinda.entities.User;
import com.basinda.requests.LoginRequest;
import com.basinda.requests.RegistrationRequest;
import com.basinda.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.basinda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserLoadService userLoadService;

    @Override
    public String login(LoginRequest request) {
        UserDetails userDetails = userLoadService.loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())){
            return "Invalid";
        }
        return "Valid";
    }

    @Override
    public User registerUser(RegistrationRequest request) {

        User user = modelMapper.map(request, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}