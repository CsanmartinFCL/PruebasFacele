package com.facele.docele.webxpc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.domain.Licencia;
import com.facele.docele.webxpc.repository.LicenciaRepository;
import com.facele.docele.webxpc.service.LicenciaService;

@Service
public class LicenciaServiceImpl implements LicenciaService{

	@Autowired
	LicenciaRepository licenciaRepository;

	@Override
	public List<Licencia> consultar(String descripcion, Integer limit, Integer offset) {
		Pageable pageable = PageRequest.of(offset, limit);

		if (descripcion != null) {
			return licenciaRepository.findAllByIdentificacion(descripcion, pageable);
		}
		return licenciaRepository.findAll(pageable).getContent();
	}

	@Override
	public Licencia crear(String licenciaIdentificacion) {
		Optional<Licencia> _licencia = licenciaRepository.findByIdentificacion(licenciaIdentificacion);
		if (_licencia.isPresent())
			return _licencia.get();

		Licencia licencia = new Licencia();
		licencia.setIdentificacion(licenciaIdentificacion);

		return licenciaRepository.save(licencia);
	}
}
