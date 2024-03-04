package com.emp.management.configuration.security;

import com.emp.management.configuration.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private  final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthEntryPoint unauthorizedHandler;

    /**
     * SECURITY FILTER CHAIN
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("SecurityFilterChain {} ");

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                auth ->
                        auth.requestMatchers("/auth/**").permitAll()
//                                .requestMatchers(HttpMethod.DELETE,"/api/vehicles/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE,"/api/tasks/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}