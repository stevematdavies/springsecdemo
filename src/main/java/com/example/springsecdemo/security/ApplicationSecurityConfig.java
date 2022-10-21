package com.example.springsecdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){

        UserDetails testUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.STUDENT.name())
                .build();

        UserDetails testAdminUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();

        UserDetails testTraineeAdminUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.ADMIN_TRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(
                testUser,
                testAdminUser,
                testTraineeAdminUser);
    }





}
