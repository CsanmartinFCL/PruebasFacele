package com.facele.docele.webxpc.service.impl;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import com.facele.docele.webxpc.TestDomain;
import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.repository.AbonadoRepository;
import com.facele.docele.webxpc.repository.dao.AbonadoDAO;
import com.facele.docele.webxpc.service.AbonadoService;

import io.facele.facilito.dto.v10.xpc.EstadoAbonado;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AbonadoServiceImplTest {

	@Mock
	AbonadoDAO abonadoDAO;
	@Mock
	AbonadoRepository abonadoRepository;
	
	AbonadoService abonadoService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		abonadoService = new AbonadoServiceImpl(abonadoDAO,abonadoRepository);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultar() {
		// given
		given(abonadoDAO.consultar(any(List.class), any(Pageable.class))).willReturn(TestDomain.getListAbonado());

		// when
		List<Abonado> consulta = abonadoService.consultar(null, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(TestDomain.getListAbonado());
	}

	
	
	@SuppressWarnings("unchecked")
	@Test
	public void ConsultarPorNombre() {
		// given
		given(abonadoDAO.consultar(any(List.class), any(Pageable.class))).willReturn(TestDomain.getListAbonado());

		// when
		List<Abonado> consulta = abonadoService.consultar(null, "john_doe", null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(TestDomain.getListAbonado());
	}

	
	
	@SuppressWarnings("unchecked")
	@Test
	public void ConsultarPorEstado() {
		// given
		given(abonadoDAO.consultar(any(List.class), any(Pageable.class))).willReturn(TestDomain.getListAbonado());

		// when
		List<Abonado> consulta = abonadoService.consultar(EstadoAbonado.ACTUALIZADO, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(TestDomain.getListAbonado());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void ConsultarPorNombreYEstado() {
		// given
		given(abonadoDAO.consultar(any(List.class), any(Pageable.class))).willReturn(TestDomain.getListAbonado());

		// when
		List<Abonado> consulta = abonadoService.consultar(EstadoAbonado.ACTUALIZADO, "john_doe", null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(TestDomain.getListAbonado());
	}

}
