package com.ecommerce.config;

import com.ecommerce.config.JwtTokenAuthenticationFilter;
import com.ecommerce.config.JwtTokenProvider;
import com.ecommerce.exceptions.ApiRequestException;
import com.ecommerce.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SecurityConfig {

    @Bean
    public Customizer<CorsConfigurer<HttpSecurity>> corsConfigurerCustomizer() {
        return httpSecurityCorsConfigurer -> new CorsRegistry()
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http, JwtTokenProvider tokenProvider, Customizer<CorsConfigurer<HttpSecurity>> corsConfigurerCustomizer) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(corsConfigurerCustomizer)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/*").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    UserDetailsService customUserDetailsService(UserRepository userRepository) {
        return (username) ->  userRepository.findByEmail(username)
                .orElseThrow(() -> new ApiRequestException("username dosn't exist",HttpStatus.NOT_FOUND));
    }

    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        return authentication -> {
            String username = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!encoder.matches(password, user.getPassword())) {
                throw new ApiRequestException("the password dosn't match the current password", HttpStatus.UNAUTHORIZED);
            }

            if (!user.isEnabled()) {
               throw new ApiRequestException("le compte de lutilisateur n'est activé",HttpStatus.FORBIDDEN) ;
            }

            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        };
    }

}
