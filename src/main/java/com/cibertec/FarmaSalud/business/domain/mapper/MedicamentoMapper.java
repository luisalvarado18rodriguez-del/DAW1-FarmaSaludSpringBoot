package com.cibertec.FarmaSalud.business.domain.mapper;

import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Medicamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicamentoMapper {

    // De Entidad a Response (Mapeamos el nombre de la categoría)
    @Mapping(source = "categoria.nombre", target = "nombreCategoria")
    MedicamentoResponseDto toResponseDto(Medicamento medicamento);

    // De Request a Entidad
    @Mapping(source = "idCategoria", target = "categoria.idCategoria")
    Medicamento toEntity(MedicamentoRequestDto requestDto);
}
