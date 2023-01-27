package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Licencia;

public interface LicenciaService {

	List<Licencia> consultar(final String descripcion, final Integer limit, final Integer offset);

	Licencia crear(final String licenciaIdentificacion);

}
