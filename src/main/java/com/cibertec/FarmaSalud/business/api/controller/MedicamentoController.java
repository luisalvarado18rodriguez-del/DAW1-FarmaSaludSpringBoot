package com.cibertec.FarmaSalud.business.api.controller;

import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoResponseDto;
import com.cibertec.FarmaSalud.business.domain.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @GetMapping
    public List<MedicamentoResponseDto> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public MedicamentoResponseDto registrar(@Valid @RequestBody MedicamentoRequestDto dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/{id}")
    public MedicamentoResponseDto actualizar(@PathVariable Long id, @Valid @RequestBody MedicamentoRequestDto dto) {
        return service.actualizar(id, dto);
    }

}