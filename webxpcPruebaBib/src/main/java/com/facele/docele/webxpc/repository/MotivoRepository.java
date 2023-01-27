package com.facele.docele.webxpc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Motivo;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {

	public List<Motivo> findAllByDescripcion(String descripcion, Pageable pageable);
	public Optional<Motivo> findByDescripcion(final String descripcion);
}
