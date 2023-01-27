package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Motivo;

public interface MotivoService {

	List<Motivo> consultar(final String descripcion, final Integer limit, final Integer offset);

	Motivo crear(final String descripcion);

}
