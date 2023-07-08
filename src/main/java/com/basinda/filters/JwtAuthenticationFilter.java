package com.basinda.filters;

import com.basinda.contants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization").substring(7);
        if (token != null){
            try{
                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser()
                        .setSigningKey(key).parseClaimsJws(token).getBody();
                String username = claims.get("username").toString();
                String role = claims.get("authorities").toString();
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception ex){
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request,response);
    }
}