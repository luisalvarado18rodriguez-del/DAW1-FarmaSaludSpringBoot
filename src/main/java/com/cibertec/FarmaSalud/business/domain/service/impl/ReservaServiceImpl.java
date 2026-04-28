package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.*;
import com.cibertec.FarmaSalud.business.data.repository.*;
import com.cibertec.FarmaSalud.business.domain.enums.EstadoReserva;
import com.cibertec.FarmaSalud.business.domain.mapper.ReservaMapper;
import com.cibertec.FarmaSalud.business.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired private ReservaRepository reservaRepo;
    @Autowired private DetalleReservaRepository detalleRepo;
    @Autowired private MedicamentoRepository medicamentoRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private ReservaMapper mapper;

    @Override
    @Transactional
    public ReservaResponseDto crearReserva(ReservaRequestDto requestDto) {
        // 1. Validar Usuario
        Usuario usuario = usuarioRepo.findById(requestDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Error: Usuario ID " + requestDto.getIdUsuario() + " no existe."));

        // 2. Crear Cabecera
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setFechaExpiracion(LocalDateTime.now().plusHours(24));
        reserva.setEstado(EstadoReserva.PENDIENTE);

        Reserva nuevaReserva = reservaRepo.save(reserva);

        // 3. Procesar Detalles
        for (var detDto : requestDto.getDetalles()) {
            Medicamento med = medicamentoRepo.findById(detDto.getIdMedicamento())
                    .orElseThrow(() -> new RuntimeException("Error: Medicamento ID " + detDto.getIdMedicamento() + " no existe."));

            // Validar Stock
            if (med.getStock() < detDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + med.getNombre());
            }

            // Lógica de Receta Médica
            if (Boolean.TRUE.equals(med.getRecetaMedica())) {
                nuevaReserva.setEstado(EstadoReserva.REQUIERE_RECETA);
                // Validar que se haya enviado la ruta de la receta
                if (detDto.getRutaReceta() == null || detDto.getRutaReceta().isEmpty()) {
                    throw new RuntimeException("El medicamento " + med.getNombre() + " requiere adjuntar una receta.");
                }
            }

            // Actualizar Stock
            med.setStock(med.getStock() - detDto.getCantidad());
            medicamentoRepo.save(med);

            // Guardar Detalle con ruta de receta
            DetalleReserva detalle = new DetalleReserva();
            detalle.setReserva(nuevaReserva);
            detalle.setMedicamento(med);
            detalle.setCantidad(detDto.getCantidad());
            detalle.setPrecioUnitario(med.getPrecio());
            detalle.setRutaReceta(detDto.getRutaReceta()); // Guardamos la ruta del archivo
            detalleRepo.save(detalle);
        }

        return mapper.toResponseDto(nuevaReserva);
    }

    @Override
    public List<ReservaResponseDto> listarTodas() {
        return reservaRepo.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}