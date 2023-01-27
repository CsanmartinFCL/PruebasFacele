package com.facele.docele.webxpc.repository;

import java.nio.file.Path;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Archivo;

@Repository
public interface ArchivoRepository extends PagingAndSortingRepository<Archivo, Long> {

	public Optional<Archivo> findByPath(final Path home);
	
}
