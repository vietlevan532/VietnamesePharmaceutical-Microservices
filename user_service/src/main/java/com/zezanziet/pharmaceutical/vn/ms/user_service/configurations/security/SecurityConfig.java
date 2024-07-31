package com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.security;

import com.zezanziet.pharmaceutical.vn.ms.user_service.configurations.jwt.JwtAuthenticationFilter;
import com.zezanziet.pharmaceutical.vn.ms.user_service.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(
                                "/user-service/api/v1/feeds",
                                "/user-service/api/v1/shops",
                                "/user-service/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("/user-service/api/v1/user")
                        .hasAnyAuthority(Role.ROLE_USER.name())
                        .requestMatchers("/user-service/api/v1/admin")
                        .hasAnyAuthority(Role.ROLE_ADMIN.name())
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
}
