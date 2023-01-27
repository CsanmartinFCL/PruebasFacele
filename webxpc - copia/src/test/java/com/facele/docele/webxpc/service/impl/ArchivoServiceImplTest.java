package com.facele.docele.webxpc.service.impl;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.facele.docele.webxpc.TestDomain;
import com.facele.docele.webxpc.domain.Archivo;
import com.facele.docele.webxpc.repository.ArchivoRepository;
import com.facele.docele.webxpc.service.ArchivoService;

public class ArchivoServiceImplTest {

	@Mock
	private ArchivoRepository archivoRepository;
	private Path home = Paths.get("src/test/resources");
	
	public ArchivoService archivoService;
	
	

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		archivoService = new ArchivoServiceImpl(home, archivoRepository);
	}

	@Test
	public void crear() throws IOException {

		// given

		// when

		Archivo archivo = archivoService.crear("Desc1".getBytes(), TestDomain.getExcepcion());

		// then
		verify(archivoRepository, times(1)).save(any(Archivo.class));
		Files.delete(Paths.get(archivo.getPath()));
	}

}
