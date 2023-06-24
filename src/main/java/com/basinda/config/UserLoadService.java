package com.basinda.config;

import com.basinda.entities.User;
import org.springframework.stereotype.Service;
import com.basinda.repositories.UserRepository;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email, password = null;
        List<GrantedAuthority> authorities = null;

        List<User> users = userRepository.findByEmail(username);

        if (users.size() == 0){
            throw new UsernameNotFoundException("User not found.");
        }
        else{
            if (!users.get(0).isEnabled() || !users.get(0).isRegistered()){
                throw new UsernameNotFoundException("User is not enable or not approved yet.");
            }
            email = users.get(0).getEmail();
            password = users.get(0).getPassword();
            authorities = new ArrayList<>();
            //authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
        }
        return new org.springframework.security.core.userdetails.User(email,password,authorities);
    }
}
