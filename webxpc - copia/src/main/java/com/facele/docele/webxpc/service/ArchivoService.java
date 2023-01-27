package com.facele.docele.webxpc.service;

import com.facele.docele.webxpc.domain.Archivo;
import com.facele.docele.webxpc.domain.Excepcion;

public interface ArchivoService {

	Archivo crear(final byte[] bytes, final Excepcion excepcion);


}
