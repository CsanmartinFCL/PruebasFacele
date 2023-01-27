package com.facele.docele.webxpc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.repository.AbonadoRepository;
import com.facele.docele.webxpc.repository.IAbonadoDAO;
import com.facele.docele.webxpc.repository.dao.AbonadoDAO;
import com.facele.docele.webxpc.service.AbonadoService;
import com.facele.docele.webxpc.util.SearchCriteria;
import com.facele.docele.webxpc.util.SearchCriteria.OPERATION;

import io.facele.facilito.dto.v10.xpc.EstadoAbonado;

@Service
public class AbonadoServiceImpl implements AbonadoService {
	
	private IAbonadoDAO abonadoDao;
	private AbonadoRepository abonadoRepository;
	
	@Autowired
	public AbonadoServiceImpl(final AbonadoDAO abonadoDAO, final AbonadoRepository abonadoRepository) {
		this.abonadoDao = abonadoDAO;
		this.abonadoRepository = abonadoRepository;
	}
	
	@Override
	public List<Abonado> consultar(EstadoAbonado estado, String nombre, String identificacion, Integer limit,
			Integer offset) {
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();

		if (estado != null) {
			params.add(new SearchCriteria("estado", OPERATION.equal, estado, null));
		}
		if (nombre != null) {
			params.add(new SearchCriteria("nombre", OPERATION.equal, nombre, null));
		}
		if (identificacion != null) {
			params.add(new SearchCriteria("identificacion",OPERATION.like, identificacion, null));
		}
		return abonadoDao.consultar(params, PageRequest.of(offset, limit));
	}

	@Override
	public Abonado obtener(String abonadoIdentificacion) {
		
		Optional<Abonado> _abonado = abonadoRepository.findByIdentificacion(abonadoIdentificacion);
		if (_abonado.isPresent())
			return _abonado.get();

		throw new IllegalArgumentException("El Abonado [ " + abonadoIdentificacion + " ] no existe en Xpc");
	}

}
