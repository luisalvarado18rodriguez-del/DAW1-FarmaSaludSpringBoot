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
public class ReservaController {

    @Autowired
    private ReservaService service;

    // Cambio conceptual en el @PostMapping
    @PostMapping(consumes = {"multipart/form-data"})
    public ReservaResponseDto crear(
            @RequestPart("reserva") ReservaRequestDto dto,
            // Agregamos required = false para que no explote si la lista va vacía
            @RequestPart(value = "archivos", required = false) List<MultipartFile> archivos) {
        return service.crearReserva(dto, archivos);
    }

    @GetMapping
    public List<ReservaResponseDto> listar() {
        return service.listarTodas();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<ReservaResponseDto> listarPorUsuario(@PathVariable Long idUsuario) {
        return service.listarPorUsuario(idUsuario);
    }
    @PatchMapping("/{id}/estado")
    public ReservaResponseDto cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return service.actualizarEstado(id, estado);
    }


}