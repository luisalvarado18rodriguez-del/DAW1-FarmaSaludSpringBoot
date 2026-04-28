package com.cibertec.FarmaSalud.business.api.controller;

import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioResponseDto;
import com.cibertec.FarmaSalud.business.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioResponseDto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public UsuarioResponseDto registrar(@Valid @RequestBody UsuarioRequestDto dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto obtenerPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}