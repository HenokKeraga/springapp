package com.example.ss7.config.provider;

import com.example.ss7.config.authenticat.CustomAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final List<String> secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var key = ((CustomAuthentication) authentication).getKey();

        if (Objects.nonNull(key) && secret.stream().anyMatch(s -> s.equals(key))) {
            return new CustomAuthentication(true, null);
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
