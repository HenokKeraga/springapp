package com.example.ss7.config.manager;

import com.example.ss7.config.authenticat.CustomAuthentication;
import com.example.ss7.config.provider.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;


public class CustomAuthenticationManager extends ProviderManager {


    public CustomAuthenticationManager(AuthenticationProvider... providers) {
        super(providers);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (getProviders().get(0).supports(authentication.getClass())) {
            return getProviders().get(0).authenticate(authentication);
        }
        super.authenticate(authentication);

        return null;
    }
}
