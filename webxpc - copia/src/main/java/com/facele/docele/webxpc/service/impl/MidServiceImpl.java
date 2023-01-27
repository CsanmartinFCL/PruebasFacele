package com.facele.docele.webxpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.domain.Mid;
import com.facele.docele.webxpc.repository.MidRepository;
import com.facele.docele.webxpc.service.MidService;

@Service
public class MidServiceImpl implements MidService {

	@Autowired
	private MidRepository midRepository;

	@Autowired
	public MidServiceImpl(MidRepository midRepository) {
		this.midRepository = midRepository;
	}
	
	@Override
	public List<Mid> consultar(final String descripcion, final Integer limit, final Integer offset) {
		Pageable pageable = PageRequest.of(offset, limit);

		if (descripcion != null) {
			return midRepository.findAllByDescripcion(descripcion, pageable);
		}
		return midRepository.findAll(pageable).getContent();
	}

	@Override
	public Mid crear(final String descripcion) {
		Optional<Mid> _mid = midRepository.findByDescripcion(descripcion);
		if (_mid.isPresent())
			return _mid.get();

		Mid mid = new Mid();
		mid.setDescripcion(descripcion);

		return midRepository.save(mid);
	}
}
