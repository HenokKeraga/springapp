package com.example.ss7.config;

import com.example.ss7.config.filter.CustomFilter;
import com.example.ss7.config.manager.CustomAuthenticationManager;
import com.example.ss7.config.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

@Configuration
//@AllArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        var customAuthenticationManager = new CustomAuthenticationManager(new CustomAuthenticationProvider(List.of("secret")));


        return httpSecurity
                .httpBasic().and()
                .addFilterAfter(new CustomFilter(customAuthenticationManager), BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated().and()
                .formLogin().and()
                .build();


    }
   @Bean
    public UserDetailsService userDetailsService() {

        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        var user = User.builder()
                .username("henok")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        inMemoryUserDetailsManager.createUser(user);

        return inMemoryUserDetailsManager;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> successEventApplicationListener() {
        return event -> System.out.println("succcclllllll");
    }
}
