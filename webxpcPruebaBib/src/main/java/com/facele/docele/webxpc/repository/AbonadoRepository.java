package com.facele.docele.webxpc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facele.docele.webxpc.domain.Abonado;

public interface AbonadoRepository extends JpaRepository<Abonado, Long>{
	public Optional<Abonado> findByIdentificacion(final String identificacion);

}
