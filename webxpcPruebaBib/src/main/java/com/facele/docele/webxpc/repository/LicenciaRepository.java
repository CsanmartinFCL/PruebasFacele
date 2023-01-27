package com.facele.docele.webxpc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Licencia;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

	public List<Licencia> findAllByIdentificacion(final String identificacion, final Pageable pageable);

	public Optional<Licencia> findByIdentificacion(final String licenciaIdentificacion);

}
