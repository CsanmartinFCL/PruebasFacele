 package com.facele.docele.webxpc.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import com.facele.docele.webxpc.TestDomain;
import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.repository.ExcepcionRepository;
import com.facele.docele.webxpc.repository.IExcepcionDAO;
import com.facele.docele.webxpc.service.AbonadoService;
import com.facele.docele.webxpc.service.ExcepcionService;
import com.facele.docele.webxpc.service.MidService;
import com.facele.docele.webxpc.service.MotivoService;
import com.facele.docele.webxpc.service.SidService;
import com.facele.docele.webxpc.service.UidService;

public class ExcepcionServiceImplTest {

	@Mock
	IExcepcionDAO excepcionDAO;
	@Mock
	MidService midService;
	@Mock
	UidService uidService;
	@Mock
	SidService sidService;
	@Mock
	AbonadoService abonadoService;
	@Mock
	MotivoService motivoService;
	@Mock
	ExcepcionRepository excepcionRepository;
	
	ExcepcionService excepcionService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		excepcionService = new ExcepcionServiceImpl(excepcionDAO, midService, uidService
				, sidService, abonadoService, motivoService, null, null, excepcionRepository);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultar() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();
		
		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(null, null, null, null, null, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarMotivo() {

		List<Excepcion> result = TestDomain.getListExcepcion();
	
		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(1l, null, null, null, null, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarMid() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();

		
		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(null, 1l, null, null, null, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarSid() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();

		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(null, null, 1l, null, null, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarAbonado() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();
		
		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(null, null, null, 1l, null, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarLicencia() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();

		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(null, null, null, null, 1l, null, null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarfechaEventoDesde() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();

		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(null, null, null, null, null, "14-14", null, 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarfechaEventoHasta() {
		
		List<Excepcion> result = TestDomain.getListExcepcion();

		// given
		given(excepcionDAO.consultar(any(List.class), any(Pageable.class))).willReturn(result);

		// when
		List<Excepcion> consulta = excepcionService.consultar(1l, null, null, null, null, null, "14-14", 100, 0);

		// then
		assertThat(consulta).isEqualTo(result);
	}
	
	
	// Test crear 
	
	
	

}