package com.cibertec.FarmaSalud.business.domain.mapper;

import com.cibertec.FarmaSalud.business.api.dto.detalleReserva.DetalleReservaResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.DetalleReserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleReservaMapper {

    @Mapping(source = "medicamento.nombre", target = "nombreMedicamento")
    DetalleReservaResponseDto toResponseDto(DetalleReserva detalle);
}