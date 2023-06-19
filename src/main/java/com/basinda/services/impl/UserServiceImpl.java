package com.basinda.services.impl;

import com.basinda.entities.User;
import org.modelmapper.ModelMapper;
import jakarta.mail.MessagingException;
import com.basinda.services.UserService;
import com.basinda.requests.LoginRequest;
import jakarta.mail.internet.MimeMessage;
import com.basinda.config.UserLoadService;
import org.springframework.stereotype.Service;
import com.basinda.repositories.UserRepository;
import com.basinda.requests.RegistrationRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private JavaMailSender mailSender;

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
    public User registerUser(RegistrationRequest request, String applicationUrl) {
        User user = modelMapper.map(request, User.class);
        String randomCode = RandomString.make(64);
        /** set email verification code and enable false*/
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User response = userRepository.save(user);
        /** send mail here */
        try {
            sendVerificationEmail(response, applicationUrl);
        }catch (Exception ex){
            // TODO : EXCEPTION HANDLE HERE
            ex.printStackTrace();
        }
        return response;
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "md.farid.ice@gmail.com";
        String senderName = "Teamwebsoft";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }
}