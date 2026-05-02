package com.cibertec.FarmaSalud.business.util;

import com.cibertec.FarmaSalud.business.data.entity.Usuario;
import com.cibertec.FarmaSalud.business.data.repository.UsuarioRepository;
import com.cibertec.FarmaSalud.business.domain.enums.RolNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setNombres("Farma");
            admin.setApellidos("Salud");


            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(RolNombre.ADMIN);

            usuarioRepository.save(admin);
            System.out.println("-----------------------------------------");
            System.out.println(">>> USUARIO INICIAL CREADO: admin / admin123");
            System.out.println("-----------------------------------------");
        }
    }
}