package com.facele.docele.webxpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.domain.Uid;
import com.facele.docele.webxpc.repository.UidRepository;
import com.facele.docele.webxpc.service.UidService;

@Service
public class UidServiceImpl implements UidService{

	@Autowired
	private UidRepository uidRepository;

	@Autowired
	public UidServiceImpl(UidRepository uidRepository) {
		this.uidRepository = uidRepository;
	}

	@Override
	public List<Uid> consultar(String descripcion, Integer limit, Integer offset) {
		Pageable pageable = PageRequest.of(offset, limit);

		if (descripcion != null) {
			return uidRepository.findAllByDescripcion(descripcion, pageable);
		}
		return uidRepository.findAll(pageable).getContent();
	}

	@Override
	public Uid crear(final String descripcion) {
		Optional<Uid> _uid = uidRepository.findByDescripcion(descripcion);
		if (_uid.isPresent())
			return _uid.get();

		Uid uid = new Uid();
		uid.setDescripcion(descripcion);

		return uidRepository.save(uid);
	}
}
