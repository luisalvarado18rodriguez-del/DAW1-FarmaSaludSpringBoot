package com.cibertec.FarmaSalud.business.domain.mapper;

import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaResponseDto toResponseDto(Categoria categoria);
    Categoria toEntity(CategoriaRequestDto requestDto);
}
