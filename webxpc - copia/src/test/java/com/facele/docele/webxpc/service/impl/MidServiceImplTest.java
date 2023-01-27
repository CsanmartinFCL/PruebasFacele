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
import com.facele.docele.webxpc.domain.Mid;
import com.facele.docele.webxpc.repository.MidRepository;
import com.facele.docele.webxpc.service.MidService;

public class MidServiceImplTest {

	@Mock
	private MidRepository midRepository;

	public MidService midService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		midService = new MidServiceImpl(midRepository);
	}

	@Test
	public void crearNoExiste() {

		// given
		given(midRepository.findByDescripcion(any(String.class))).willReturn(Optional.empty());

		// when

		midService.crear("descripcion");

		// then
		verify(midRepository, times(1)).save(any(Mid.class));
	}

	@Test
	public void crearExiste() {
		// given
		given(midRepository.findByDescripcion(any(String.class))).willReturn(Optional.of(TestDomain.getMid()));

		// when

		midService.crear("descripcion");

		// then
		verify(midRepository, times(0)).save(any(Mid.class));
	}

}
