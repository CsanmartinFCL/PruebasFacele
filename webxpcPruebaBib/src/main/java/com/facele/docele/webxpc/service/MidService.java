package com.facele.docele.webxpc.service;

import java.util.List;

import com.facele.docele.webxpc.domain.Mid;

public interface MidService {

	List<Mid> consultar(final String descripcion, final Integer limit, final Integer offset);

	Mid crear(final String mid);


}
