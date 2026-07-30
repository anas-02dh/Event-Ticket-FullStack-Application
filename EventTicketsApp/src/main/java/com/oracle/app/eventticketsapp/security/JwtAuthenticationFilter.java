package com.oracle.app.eventticketsapp.security;

import com.oracle.app.eventticketsapp.entities.User;
import com.oracle.app.eventticketsapp.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request
    ) {

        String path = request.getServletPath();

        return path.equals("/login")
                || path.equals("/register")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    ) throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        System.out.println(
                "Request: "
                        + request.getMethod()
                        + " "
                        + request.getRequestURI()
        );

        System.out.println(
                "Authorization: "
                        + authHeader
        );

        // No token
        if (
                authHeader == null
                        || !authHeader.startsWith("Bearer ")
        ) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        String jwt =
                authHeader.substring(7);

        try {

            String userId =
                    jwtService.extractUserId(jwt);

            System.out.println(
                    "User ID from JWT: "
                            + userId
            );

            if (
                    userId != null
                            && SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            == null
            ) {

                User user =
                        userRepository
                                .findById(userId)
                                .orElse(null);

                if (user == null) {

                    System.out.println(
                            "USER NOT FOUND"
                    );

                    filterChain.doFilter(
                            request,
                            response
                    );

                    return;
                }

                System.out.println(
                        "User email: "
                                + user.getEmail()
                );

                System.out.println(
                        "User role: "
                                + user.getRole()
                );

                boolean valid =
                        jwtService.isTokenValid(
                                jwt,
                                user
                        );

                System.out.println(
                        "Token valid: "
                                + valid
                );

                if (valid) {

                    String role =
                            "ROLE_"
                                    + user
                                    .getRole()
                                    .name();

                    System.out.println(
                            "Authority: "
                                    + role
                    );

                    SimpleGrantedAuthority authority =
                            new SimpleGrantedAuthority(
                                    role
                            );

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(

                                    user.getEmail(),

                                    null,

                                    List.of(authority)

                            );

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    authentication
                            );

                    System.out.println(
                            "Authentication set successfully"
                    );
                }
            }

        } catch (Exception exception) {

            System.out.println(
                    "JWT ERROR: "
                            + exception.getMessage()
            );
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}