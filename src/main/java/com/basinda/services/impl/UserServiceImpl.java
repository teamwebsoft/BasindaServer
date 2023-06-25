package com.basinda.services.impl;

import com.basinda.contants.SecurityConstants;
import com.basinda.entities.User;
import com.basinda.exceptions.ResourceNotFoundException;
import com.basinda.utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import jakarta.mail.MessagingException;
import com.basinda.services.UserService;
import com.basinda.requests.LoginRequest;
import jakarta.mail.internet.MimeMessage;
import com.basinda.config.UserLoadService;
import com.basinda.requests.ApproveRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserLoadService userLoadService;


    @Override
    public String login(LoginRequest request, final HttpServletResponse res) throws ResourceNotFoundException {
        UserDetails userDetails = userLoadService.loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())){
            throw new ResourceNotFoundException("Username or password does not match.");
        }
        Authentication authentication;
        try {
            authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtil.generateToken();
            res.setHeader(SecurityConstants.JWT_HEADER,token);
        }
        catch (BadCredentialsException ex){
            return "Invalid";
        }
        return "valid";
    }

    @Override
    public User registerUser(RegistrationRequest request, String applicationUrl) {
        User user = modelMapper.map(request, User.class);
        String randomCode = RandomString.make(64);
        /** set email verification code and enable false*/
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        user.setRegistered(false);
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

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()){
            return false;
        }
        else{
            user.setEnabled(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public boolean approveUser(ApproveRequest request, String applicationUrl)  {
        User user = userRepository.findById(request.getUserId()).orElseThrow( () -> new RuntimeException("User not found."));
        if (user != null){
            try {
                sendConfirmEmail(user, applicationUrl);
                user.setRegistered(true);
                User response = userRepository.save(user);
                if (response != null){
                    return true;
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return false;
    }

    /** for verify email sender*/
    private void sendVerificationEmail(User user, String applicationUrl)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "md.farid.ice@gmail.com";
        String senderName = "Teamwebsoft";
        String subject = "Verification";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Teamwebsoft";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getName());
        String verifyUrl = applicationUrl + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyUrl);

        helper.setText(content, true);

        mailSender.send(message);
    }

    /** for confirmation email sender*/
    private void sendConfirmEmail(User user, String applicationUrl)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "md.farid.ice@gmail.com";
        String senderName = "Teamwebsoft";
        String subject = "Confirmation";
        String content = "Dear [[name]],<br>"
                + "Your Account Approved You Can Login Now."
                + "Thank you,<br>"
                + "Teamwebsoft";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getName());
        helper.setText(content, true);
        mailSender.send(message);
    }
}