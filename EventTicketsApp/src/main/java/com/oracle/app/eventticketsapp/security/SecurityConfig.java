package com.oracle.app.eventticketsapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(

            HttpSecurity http
    ) throws Exception {

        http
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // CORS preflight
                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        )
                        .permitAll()

                        // PUBLIC AUTHENTICATION

                        .requestMatchers(
                                "/login",
                                "/register"
                        ).permitAll()



                        // SWAGGER / OPENAPI

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()





                        // ADMIN

                        .requestMatchers(
                                "/users/**",
                                "/categories/**",
                                "/tickets",
                                "/admin/dashboard",
                                "/admin/dashboard/**",
                                "/admin/users",
                                "/admin/users/**",
                                "/admin/events",
                                "/admin/events/**"
                        ).hasRole("ADMIN")



                        // CUSTOMER

                        .requestMatchers(
                                "/tickets/purchase/**",
                                "/tickets/purchase/history/**"
                        ).hasRole("CUSTOMER")


                        .requestMatchers(
                                "/events/**"
                        )
                        .hasAnyRole(
                                "ADMIN", "CUSTOMER"
                        )


                        // OTHER REQUESTS

                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(
                         jwtAuthenticationFilter,
                         UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:4200")
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}