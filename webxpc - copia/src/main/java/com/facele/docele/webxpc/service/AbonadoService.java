package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Abonado;

import io.facele.facilito.dto.v10.xpc.EstadoAbonado;

public interface AbonadoService {

	List<Abonado> consultar(final EstadoAbonado estado, final String nombre, final String identificacion,
			final Integer limit, final Integer offset);

	Abonado obtener(final String abonadoIdentificacion);

}
