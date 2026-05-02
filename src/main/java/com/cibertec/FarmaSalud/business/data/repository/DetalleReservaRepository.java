package com.cibertec.FarmaSalud.business.data.repository;

import com.cibertec.FarmaSalud.business.data.entity.DetalleReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva, Long>  {
}
