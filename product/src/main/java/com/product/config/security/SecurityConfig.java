package com.product.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product.config.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfig {

        @Autowired
        private JwtAuthFilter jwtFilter;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {

                http.csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(
                                                auth -> auth
                                                                .requestMatchers("/error", "/swagger-ui/**",
                                                                                "/v3/api-docs/**", "/actuator/info",
                                                                                "/actuator/health")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.GET, "/category")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.GET, "/category/active")
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.POST, "/category")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, "/category/{id}")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PATCH,
                                                                                "/category/{id}/enable")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PATCH,
                                                                                "/category/{id}/disable")
                                                                .hasAuthority("ADMIN")

                                                                .requestMatchers(HttpMethod.GET, "/product")
                                                                .authenticated()
                                                                .requestMatchers(HttpMethod.GET,
                                                                                "/product/{id_product}")
                                                                .authenticated()
                                                                .requestMatchers(HttpMethod.POST, "/product")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT,
                                                                                "/product/{id_product}")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PATCH,
                                                                                "/product/{id_product}/enable")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.PATCH,
                                                                                "/product/{id_product}/disable")
                                                                .hasAuthority("ADMIN")

                                                                .requestMatchers(HttpMethod.GET,
                                                                                "/product/{id_product}/image")
                                                                .authenticated()
                                                                .requestMatchers(HttpMethod.POST,
                                                                                "/product/{id_product}/image")
                                                                .hasAuthority("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE,
                                                                                "/product/{id_product}/image/{product_image_id}")
                                                                .hasAuthority("ADMIN"))
                                .cors(cors -> cors.configurationSource(corsConfig))
                                .httpBasic(Customizer.withDefaults())
                                .formLogin(form -> form.disable())
                                .sessionManagement(
                                                httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
