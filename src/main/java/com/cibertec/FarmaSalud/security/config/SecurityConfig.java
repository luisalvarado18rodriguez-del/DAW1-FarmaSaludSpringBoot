package com.cibertec.FarmaSalud.security.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // 1. Opciones de navegador
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                        // 2. Acceso público a Usuarios (Login y Registro)
                        .requestMatchers("/api/usuarios/login", "/api/usuarios/registro").permitAll()
                        .requestMatchers("/api/usuarios/**").permitAll()

                        // 3. Medicamentos: Restauramos permisos explícitos para asegurar el CRUD del Admin
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/medicamentos/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/medicamentos/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/medicamentos/**").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/medicamentos/**").permitAll()

                        // 4. Categorías y Recursos estáticos
                        .requestMatchers("/api/categorias/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()

                        // 5. Reservas y Manejo de Errores (Nuevas rutas necesarias)[cite: 1, 2]
                        .requestMatchers("/api/reservas/**", "/api/reservas", "/error").permitAll()

                        // 6. Resto de peticiones
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}