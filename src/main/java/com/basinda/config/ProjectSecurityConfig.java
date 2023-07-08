package com.basinda.config;

import com.basinda.filters.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class ProjectSecurityConfig {

    List<String> whiteListUrl = List.of("/auth/register","/auth/login", "/divisions", "/districts/**","/pourosovas/**","/verify/**");

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedOrigins(Collections.singletonList("*"));
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setExposedHeaders(Arrays.asList("Authorization"));
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                }).and()
                .csrf().disable()
                .authorizeHttpRequests(
                        r -> r.requestMatchers(
                                        whiteListUrl.stream()
                                                .map(AntPathRequestMatcher::new)
                                                .toArray(RequestMatcher[]::new)
                                )
                                .permitAll()
                                .requestMatchers("/admin/**").hasAuthority("eAdmin")
                                .anyRequest()
                                .authenticated()
                ).addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                //.requestMatchers("/actuator","/auth/register","/auth/login").permitAll()
                .httpBasic()
                .and().formLogin()
                .and().build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(whiteListUrl.toArray(String[]::new));
    }
}