package com.hecto.mylogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// 권한(ROLE_USER, ROLE_ADMIN 등)에 따라 접근 권한설정
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(
                        sessionManagement ->
                                sessionManagement.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                //                        request ->
                //                                request.requestMatchers("/", "/api/v1/auth/**",
                // "oauth2/callback/*")
                //                                        .permitAll()
                //                                        .requestMatchers("api/v1/user/")
                //                                        .hasRole("ROLE_USER")
                //                                        .requestMatchers("api/v1/admin/**")
                //                                        .hasRole("ROLE_ADMIN")
                //                                        .anyRequest()
                //                                        .authenticated())
                .oauth2Login(
                        oauth2 -> oauth2.defaultSuccessUrl("/").failureUrl("/login?error=true"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/main"));

        return httpSecurity.build();
    }

    // Cors 정책 설정
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
