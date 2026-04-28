package com.cibertec.FarmaSalud.business.api.controller;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import com.cibertec.FarmaSalud.business.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ReservaResponseDto crear(@RequestBody ReservaRequestDto dto) {
        return service.crearReserva(dto);
    }

    @GetMapping
    public List<ReservaResponseDto> listar() {
        return service.listarTodas();
    }


}