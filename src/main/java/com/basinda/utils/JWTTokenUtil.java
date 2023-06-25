package com.basinda.utils;

import com.basinda.contants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JWTTokenUtil {

    public String generateToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("Teamwebsoft").setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date().getTime()) + 30000000))
                    .signWith(key).compact();
            return jwt;
        }
        else{
            return "";
        }
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority > authorities) {
        Set<String> authority = new HashSet<>();
        for (GrantedAuthority grantedAuthority: authorities){
            authority.add(grantedAuthority.getAuthority());
        }
        return String.join(",",authority);
    }
}