package com.basinda.filters;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import com.basinda.contants.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.io.IOException;
import java.util.Collection;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication){
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("Teamwebsoft").setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date().getTime())+30000000))
                    .signWith(key).compact();
            response.setHeader(SecurityConstants.JWT_HEADER,jwt);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/auth/login");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authority = new HashSet<>();
        for (GrantedAuthority grantedAuthority: authorities){
            authority.add(grantedAuthority.getAuthority());
        }
        return String.join(",",authority);
    }
}