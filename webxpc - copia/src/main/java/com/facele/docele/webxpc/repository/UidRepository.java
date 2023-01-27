package com.facele.docele.webxpc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Uid;

@Repository
public interface UidRepository extends PagingAndSortingRepository<Uid, Long> {

	public List<Uid> findAllByDescripcion(final String descripcion, final Pageable pageable);
	public Optional<Uid> findByDescripcion(final String descripcion);
	

}
