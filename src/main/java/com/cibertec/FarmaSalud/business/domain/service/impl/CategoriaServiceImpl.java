package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Categoria;
import com.cibertec.FarmaSalud.business.data.repository.CategoriaRepository;
import com.cibertec.FarmaSalud.business.domain.mapper.CategoriaMapper;
import com.cibertec.FarmaSalud.business.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    @Autowired
    private CategoriaMapper mapper;

    @Override
    public List<CategoriaResponseDto> listarTodas() {
        return repo.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDto guardar(CategoriaRequestDto requestDto) {
        Categoria entidad = mapper.toEntity(requestDto);
        Categoria guardada = repo.save(entidad);
        return mapper.toResponseDto(guardada);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}