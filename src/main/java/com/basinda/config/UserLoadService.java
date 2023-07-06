package com.basinda.config;

import com.basinda.models.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.basinda.repositories.UserRepository;
import com.basinda.exceptions.ResourceNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.ArrayList;

@Service
public class UserLoadService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, ResourceNotFoundException {
        String phone, password = null;
        List<GrantedAuthority> authorities = null;

        List<User> users = userRepository.findByMobileNumber(username);

        if (users.size() == 0){
            throw new ResourceNotFoundException("User is not registered yet. Please register first");
        }
        else{
            if (!users.get(0).getEnabled() || !users.get(0).getIsRegistered()){
                throw new ResourceNotFoundException("User is not enable or not approved yet.");
            }
            phone = users.get(0).getMobileNumber();
            password = users.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("User"));
        }
        return new org.springframework.security.core.userdetails.User(phone,password,authorities);
    }
}
