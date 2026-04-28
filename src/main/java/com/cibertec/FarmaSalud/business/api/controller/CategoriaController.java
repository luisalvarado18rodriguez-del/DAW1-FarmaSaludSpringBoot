package com.cibertec.FarmaSalud.business.api.controller;

import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaResponseDto;
import com.cibertec.FarmaSalud.business.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    public List<CategoriaResponseDto> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public CategoriaResponseDto registrar(@Valid @RequestBody CategoriaRequestDto dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}