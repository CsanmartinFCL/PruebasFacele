package com.facele.docele.webxpc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Sid;

@Repository
public interface SidRepository extends PagingAndSortingRepository<Sid, Long> {

	public List<Sid> findAllByDescripcion(final String descripcion, final Pageable pageable);
	public Optional<Sid> findByDescripcion(final String descripcion);
}
