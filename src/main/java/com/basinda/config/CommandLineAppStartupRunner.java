package com.basinda.config;

import com.basinda.contants.PropertiesConstants;
import com.basinda.models.eGenderType;
import com.basinda.models.eUserType;
import com.basinda.models.entity.Profession;
import com.basinda.models.entity.Properties;
import com.basinda.models.entity.User;
import com.basinda.repositories.ProfessionRepository;
import com.basinda.repositories.PropertiesRepository;
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

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private ProfessionRepository professionRepository;


    @Override
    public void run(String... args) throws Exception {
        /** User user = new User();
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

        Properties properties = new Properties();
        properties.setProperty(PropertiesConstants.twoFactorEnabled);
        properties.setValue("False");
        propertiesRepository.save(properties);*/

        Profession profession1 = new Profession();
        profession1.setName("Student");
        professionRepository.save(profession1);

        Profession profession2 = new Profession();
        profession2.setName("Govt Job");
        professionRepository.save(profession2);

        Profession profession3 = new Profession();
        profession3.setName("Private Job");
        professionRepository.save(profession3);

        Profession profession4 = new Profession();
        profession4.setName("Business");
        professionRepository.save(profession4);

        Profession profession5 = new Profession();
        profession5.setName("Hocker");
        professionRepository.save(profession5);

        Profession profession6 = new Profession();
        profession6.setName("Unemployed");
        professionRepository.save(profession6);
    }
}
