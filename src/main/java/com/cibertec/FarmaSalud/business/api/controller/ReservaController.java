package com.cibertec.FarmaSalud.business.api.controller;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import com.cibertec.FarmaSalud.business.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {

    @Autowired
    private ReservaService service;

    // Cambio conceptual en el @PostMapping
    @PostMapping(consumes = {"multipart/form-data"})
    public ReservaResponseDto crear(
            @RequestPart("reserva") ReservaRequestDto dto,
            @RequestPart("archivos") List<MultipartFile> archivos) {
        // Aquí llamaremos al servicio pasando el DTO y los archivos reales
        return service.crearReserva(dto, archivos);
    }

    @GetMapping
    public List<ReservaResponseDto> listar() {
        return service.listarTodas();
    }


}