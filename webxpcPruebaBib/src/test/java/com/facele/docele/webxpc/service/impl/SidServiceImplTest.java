package com.facele.docele.webxpc.service.impl;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Optional;

import com.facele.docele.webxpc.TestDomain;
import com.facele.docele.webxpc.domain.Sid;
import com.facele.docele.webxpc.repository.SidRepository;
import com.facele.docele.webxpc.service.SidService;

public class SidServiceImplTest {

	@Mock
	private SidRepository sidRepository;

	public SidService sidService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		sidService = new SidServiceImpl(sidRepository);
	}

	@Test
	public void crearNoExiste() {

		// given
		given(sidRepository.findByDescripcion(any(String.class))).willReturn(Optional.empty());

		// when

		sidService.crear("descripcion");

		// then
		verify(sidRepository, times(1)).save(any(Sid.class));
	}

	@Test
	public void crearExiste() {
		// given
		given(sidRepository.findByDescripcion(any(String.class))).willReturn(Optional.of(TestDomain.getSid()));

		// when

		sidService.crear("descripcion");

		// then
		verify(sidRepository, times(0)).save(any(Sid.class));
	}

}
