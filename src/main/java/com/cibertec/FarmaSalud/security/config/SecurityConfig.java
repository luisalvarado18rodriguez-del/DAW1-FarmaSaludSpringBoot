package com.cibertec.FarmaSalud.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Crucial para que funcionen los POST
                .cors(cors -> cors.disable()) // Desactiva CORS temporalmente para evitar bloqueos
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll() // CAMBIO: Permitir todo provisionalmente para la exposición
                )
                // Comenta o elimina el formLogin temporalmente para Bruno
                /* .formLogin(form -> form
                    .defaultSuccessUrl("/api/medicamentos", true)
                    .permitAll()
                ) */
                .httpBasic(Customizer.withDefaults()); // Esto permite que si pide login, Bruno pueda enviarlo sin redirecciones

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // Creamos un usuario en memoria como en el PDF de clase [cite: 298-303]
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}