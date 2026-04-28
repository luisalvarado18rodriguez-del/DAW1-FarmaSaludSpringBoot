package com.cibertec.FarmaSalud.business.data.repository;

import com.cibertec.FarmaSalud.business.data.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}