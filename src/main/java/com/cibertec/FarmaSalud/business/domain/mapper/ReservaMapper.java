package com.cibertec.FarmaSalud.business.domain.mapper;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DetalleReservaMapper.class})
public interface ReservaMapper {
    @Mapping(source = "usuario.username", target = "username")
    @Mapping(source = "detalles", target = "detalles")
    ReservaResponseDto toResponseDto(Reserva reserva);
}