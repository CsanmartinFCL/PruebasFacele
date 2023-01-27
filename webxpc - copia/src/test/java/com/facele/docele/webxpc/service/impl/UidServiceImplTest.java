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
import com.facele.docele.webxpc.domain.Uid;
import com.facele.docele.webxpc.repository.UidRepository;
import com.facele.docele.webxpc.service.UidService;

public class UidServiceImplTest {

	@Mock
	private UidRepository uidRepository;

	public UidService uidService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		uidService = new UidServiceImpl(uidRepository);
	}

	@Test
	public void crearNoExiste() {

		// given
		given(uidRepository.findByDescripcion(any(String.class))).willReturn(Optional.empty());

		// when

		uidService.crear("descripcion");

		// then
		verify(uidRepository, times(1)).save(any(Uid.class));
	}

	@Test
	public void crearExiste() {
		// given
		given(uidRepository.findByDescripcion(any(String.class))).willReturn(Optional.of(TestDomain.getUid()));

		// when

		uidService.crear("descripcion");

		// then
		verify(uidRepository, times(0)).save(any(Uid.class));
	}

}
