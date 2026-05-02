package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.*;
import com.cibertec.FarmaSalud.business.data.repository.*;
import com.cibertec.FarmaSalud.business.domain.enums.EstadoReserva;
import com.cibertec.FarmaSalud.business.domain.mapper.ReservaMapper;
import com.cibertec.FarmaSalud.business.domain.service.FileStorageService;
import com.cibertec.FarmaSalud.business.domain.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired private FileStorageService fileStorageService;

    @Override
    @Transactional
    public ReservaResponseDto crearReserva(ReservaRequestDto requestDto, List<MultipartFile> archivos) {
        // 1. Validar Usuario
        Usuario usuario = usuarioRepo.findById(requestDto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Error: Usuario ID " + requestDto.getIdUsuario() + " no existe."));

        // 2. Crear Cabecera
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setFechaExpiracion(LocalDateTime.now().plusHours(24));
        reserva.setEstado(EstadoReserva.PENDIENTE);

        // Guardamos inicialmente para obtener el ID de la reserva
        Reserva nuevaReserva = reservaRepo.save(reserva);

        int indiceArchivo = 0; // Contador para recorrer la lista de archivos MultipartFile

        // 3. Procesar Detalles
        for (var detDto : requestDto.getDetalles()) {
            Medicamento med = medicamentoRepo.findById(detDto.getIdMedicamento())
                    .orElseThrow(() -> new RuntimeException("Error: Medicamento ID " + detDto.getIdMedicamento() + " no existe."));

            // Validar Stock
            if (med.getStock() < detDto.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + med.getNombre());
            }

            String nombreArchivoFinal = null;

            // Lógica de Receta Médica
            if (Boolean.TRUE.equals(med.getRecetaMedica())) {
                nuevaReserva.setEstado(EstadoReserva.REQUIERE_RECETA);

                // Validar que el archivo físico exista en la lista recibida
                if (archivos == null || indiceArchivo >= archivos.size()) {
                    throw new RuntimeException("Falta adjuntar el archivo de receta para: " + med.getNombre());
                }

                // GUARDADO FÍSICO: Llamamos al servicio para guardar en la carpeta 'recetas'
                MultipartFile archivoFisico = archivos.get(indiceArchivo);
                nombreArchivoFinal = fileStorageService.guardarArchivo(archivoFisico, "recetas");

                indiceArchivo++; // Incrementamos para el siguiente medicamento que pida receta
            }

            // Actualizar Stock
            med.setStock(med.getStock() - detDto.getCantidad());
            medicamentoRepo.save(med);

            // Guardar Detalle con la ruta real generada
            DetalleReserva detalle = new DetalleReserva();
            detalle.setReserva(nuevaReserva);
            detalle.setMedicamento(med);
            detalle.setCantidad(detDto.getCantidad());
            detalle.setPrecioUnitario(med.getPrecio());
            detalle.setRutaReceta(nombreArchivoFinal); // Guardamos el nombre único (UUID)
            detalleRepo.save(detalle);
        }

        // Guardamos los cambios de estado finales en la cabecera si hubo recetas
        reservaRepo.save(nuevaReserva);

        return mapper.toResponseDto(nuevaReserva);
    }

    @Override
    public List<ReservaResponseDto> listarTodas() {
        return reservaRepo.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<ReservaResponseDto> listarPorUsuario(Long idUsuario) {
        // Usamos el repositorio para buscar por el ID del usuario
        return reservaRepo.findByUsuarioIdUsuario(idUsuario).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public ReservaResponseDto actualizarEstado(Long idReserva, String nuevoEstado) {
        Reserva reserva = reservaRepo.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(EstadoReserva.valueOf(nuevoEstado.toUpperCase()));
        return mapper.toResponseDto(reservaRepo.save(reserva));
    }
}