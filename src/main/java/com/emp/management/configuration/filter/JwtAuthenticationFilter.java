package com.emp.management.configuration.filter;

import com.emp.management.service.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

/*
SECURITY FILTER FOR JWT
* */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("Processing authentication for '{}'", request.getRequestURL());

        String HEADER = "Authorization";
        final String header = request.getHeader(HEADER);
        String jwt = null;
        String email = null;
        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
            email = jwtService.extractUsername(jwt);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("Setting security context");

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (jwtService.validateToken(jwt, userDetails)) {
                log.info("Token is valid");

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                log.info("Authenticated user '{}', setting security context", email);

                SecurityContextHolder.getContext().setAuthentication(authToken);

                log.info("Security context holder updated with the authenticated user '{}'", email);
            }
        }
        filterChain.doFilter(request, response);
        log.info("Request processed");
    }
}
