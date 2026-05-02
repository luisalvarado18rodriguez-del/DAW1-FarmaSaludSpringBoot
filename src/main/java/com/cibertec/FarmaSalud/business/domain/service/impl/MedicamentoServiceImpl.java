package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Medicamento;
import com.cibertec.FarmaSalud.business.data.repository.CategoriaRepository;
import com.cibertec.FarmaSalud.business.data.repository.MedicamentoRepository;
import com.cibertec.FarmaSalud.business.domain.mapper.MedicamentoMapper;
import com.cibertec.FarmaSalud.business.domain.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository repo;

    @Autowired
    private MedicamentoMapper mapper;
    @Autowired
    private CategoriaRepository categoriaRepo;

    @Override
    public List<MedicamentoResponseDto> listarTodos() {
        // Convertimos la lista de entidades a una lista de DTOs usando el mapper
        return repo.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentoResponseDto guardar(MedicamentoRequestDto requestDto) {
        // 1. Convertimos el DTO de entrada a Entidad
        Medicamento entidad = mapper.toEntity(requestDto);
        // 2. Guardamos en la BD
        Medicamento guardado = repo.save(entidad);
        // 3. Devolvemos el DTO de respuesta
        return mapper.toResponseDto(guardado);
    }

    @Override
    public MedicamentoResponseDto buscarPorId(Long id) {
        Medicamento encontrado = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        return mapper.toResponseDto(encontrado);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public MedicamentoResponseDto actualizar(Long id, MedicamentoRequestDto requestDto) {
        return repo.findById(id).map(medicamento -> {

            medicamento.setNombre(requestDto.getNombre());
            medicamento.setPrecio(requestDto.getPrecio());
            medicamento.setStock(requestDto.getStock());
            medicamento.setFechaVencimiento(requestDto.getFechaVencimiento());
            medicamento.setRecetaMedica(requestDto.getRecetaMedica());


            if (requestDto.getIdCategoria() != null) {
                categoriaRepo.findById(requestDto.getIdCategoria())
                        .ifPresent(medicamento::setCategoria);
            }


            if (requestDto.getRutaImagen() != null) {
                medicamento.setRutaImagen(requestDto.getRutaImagen());
            }

            Medicamento actualizado = repo.save(medicamento);
            return mapper.toResponseDto(actualizado);

        }).orElseThrow(() -> new RuntimeException("No se encontró el medicamento con ID: " + id));
    }
}