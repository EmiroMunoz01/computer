package com.computer.computer.Configuration.Security;

import com.computer.computer.DTO.User.UserDTO;
import com.computer.computer.Entity.UserEntity;
import com.computer.computer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;


@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request
                        .requestMatchers("/home/**", "/store/user/create").permitAll()
                        .requestMatchers("/store/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        
        return email -> {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));

            return User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(authorities) // Asignación de roles
                    .build();
        };
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
