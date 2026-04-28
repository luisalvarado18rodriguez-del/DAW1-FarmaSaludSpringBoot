package com.cibertec.FarmaSalud.business.data.repository;

import com.cibertec.FarmaSalud.business.data.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    // Aquí podrías agregar métodos personalizados si los necesitas después
}