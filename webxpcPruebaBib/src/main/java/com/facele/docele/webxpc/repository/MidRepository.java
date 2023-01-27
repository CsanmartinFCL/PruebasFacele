package com.facele.docele.webxpc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Mid;

@Repository
public interface MidRepository extends PagingAndSortingRepository<Mid, Long> {

	public List<Mid> findAllByDescripcion(final String descripcion, final Pageable pageable);
	public Optional<Mid> findByDescripcion(final String descripcion);
}
