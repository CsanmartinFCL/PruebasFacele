package com.facele.docele.webxpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.domain.Sid;
import com.facele.docele.webxpc.repository.SidRepository;
import com.facele.docele.webxpc.service.SidService;

@Service
public class SidServiceImpl implements SidService {

	@Autowired
	private SidRepository sidRepository;

	public SidServiceImpl(SidRepository sidRepository) {
		this.sidRepository = sidRepository;
	}

	@Override
	public List<Sid> consultar(String descripcion, Integer limit, Integer offset) {
		Pageable pageable = PageRequest.of(offset, limit);

		if (descripcion != null) {
			return sidRepository.findAllByDescripcion(descripcion, pageable);
		}
		return sidRepository.findAll(pageable).getContent();
	}

	@Override
	public Sid crear(final String descripcion) {
		Optional<Sid> _sid = sidRepository.findByDescripcion(descripcion);
		if (_sid.isPresent())
			return _sid.get();

		Sid sid = new Sid();
		sid.setDescripcion(descripcion);

		return sidRepository.save(sid);
	}
}
