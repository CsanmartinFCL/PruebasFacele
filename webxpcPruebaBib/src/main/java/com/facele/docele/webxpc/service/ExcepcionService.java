package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Excepcion;

import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;

public interface ExcepcionService {

	List<Excepcion> consultar(final Long motivoId, final Long midId, final Long sidId, 
			final Long abonadoId, final Long licenciaId, final String fechaEventoDesde, 
			final String fechaEventoHasta, final Integer limit, final Integer offset);
	
	
	Excepcion obetener(final Long id);


	Excepcion crear(final InputExcepcionCrear inputDTO);

	
	
	
}