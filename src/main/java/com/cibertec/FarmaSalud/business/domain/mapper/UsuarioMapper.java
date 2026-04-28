package com.cibertec.FarmaSalud.business.domain.mapper;

import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponseDto toResponseDto(Usuario usuario);
    Usuario toEntity(UsuarioRequestDto requestDto);
}