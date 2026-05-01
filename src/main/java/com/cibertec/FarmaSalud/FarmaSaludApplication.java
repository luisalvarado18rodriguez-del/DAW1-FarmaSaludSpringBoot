package com.cibertec.FarmaSalud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class FarmaSaludApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaSaludApplication.class, args);
	}

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Esto registra automáticamente todos los módulos disponibles (incluyendo fechas)
        mapper.findAndRegisterModules();
        // Evita que las fechas se vean como arreglos de números [2026, 5, 1]
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
