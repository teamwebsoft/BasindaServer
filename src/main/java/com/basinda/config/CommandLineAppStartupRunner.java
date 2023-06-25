package com.basinda.config;

import com.basinda.entities.User;
import com.basinda.models.eGenderType;
import com.basinda.models.eUserType;
import com.basinda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Date;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setNid("123456789");
        user.setName("Admin");
        user.setGenderType(eGenderType.eMale);
        user.setBirthday(new Date(1995,20,10));
        user.setMobileNumber("01315656967");
        user.setEmail("admin@gmail.com");
        user.setProfession("Software Eng.");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setEnabled(Boolean.TRUE);
        user.setIsRegistered(Boolean.TRUE);
        user.setUserType(eUserType.eAdmin);
        userRepository.save(user);
    }
}
