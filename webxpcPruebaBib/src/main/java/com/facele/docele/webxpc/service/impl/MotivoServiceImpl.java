package com.facele.docele.webxpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.domain.Motivo;
import com.facele.docele.webxpc.repository.MotivoRepository;
import com.facele.docele.webxpc.service.MotivoService;

@Service
public class MotivoServiceImpl implements MotivoService {

	@Autowired
	MotivoRepository motivoRepository;

	@Override
	public List<Motivo> consultar(String descripcion, Integer limit, Integer offset) {
		Pageable pageable = PageRequest.of(offset, limit);

		if (descripcion != null) {
			return motivoRepository.findAllByDescripcion(descripcion, pageable);
		}
		return motivoRepository.findAll(pageable).getContent();
	}

	@Override
	public Motivo crear(String descripcion) {
		Optional<Motivo> _motivo = motivoRepository.findByDescripcion(descripcion);
		if (_motivo.isPresent())
			return _motivo.get();

		Motivo motivo = new Motivo();
		motivo.setDescripcion(descripcion);

		return motivoRepository.save(motivo);
	}
}
