package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Uid;

public interface UidService {

	List<Uid> consultar(final String descripcion, final Integer limit, final Integer offset);

	Uid crear(final String uid);

	
}
