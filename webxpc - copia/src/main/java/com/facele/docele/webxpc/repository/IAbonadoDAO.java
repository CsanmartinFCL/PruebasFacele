package com.facele.docele.webxpc.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.util.SearchCriteria;

public interface IAbonadoDAO {

	List<Abonado> consultar(final List<SearchCriteria> params, final Pageable pageable);
}
