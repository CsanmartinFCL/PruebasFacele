package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Sid;

public interface SidService {

	List<Sid> consultar( String descripcion, Integer limit, Integer offset);

	Sid crear(final String sid);


	
}
