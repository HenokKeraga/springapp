package com.example.ss7.config.filter;

import com.example.ss7.config.authenticat.CustomAuthentication;
import com.example.ss7.config.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@AllArgsConstructor
public class CustomFilter extends OncePerRequestFilter {

    final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var key = request.getHeader("key");
        if (Objects.isNull(key) || "null".equals(key)) {
            filterChain.doFilter(request, response);
        }

        var toAuthenticate = new CustomAuthentication(false, key);
        var authenticate = customAuthenticationManager.authenticate(toAuthenticate);


        if (Objects.nonNull(authenticate) && authenticate.isAuthenticated()) {

            var emptyContext = SecurityContextHolder.createEmptyContext();
            emptyContext.setAuthentication(authenticate);
            SecurityContextHolder.setContext(emptyContext);

            filterChain.doFilter(request, response);
        }
        response.setStatus(HttpStatus.FORBIDDEN.value());
        // response.getWriter().println("wrong secret");
        return;
    }
}
