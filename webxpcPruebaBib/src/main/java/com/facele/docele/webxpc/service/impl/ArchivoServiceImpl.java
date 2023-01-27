package com.facele.docele.webxpc.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facele.docele.webxpc.Constantes.EstadoArchivo;
import com.facele.docele.webxpc.domain.Archivo;
import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.repository.ArchivoRepository;
import com.facele.docele.webxpc.service.ArchivoService;

@Service

public class ArchivoServiceImpl implements ArchivoService {

	private ArchivoRepository archivoRepository;

	Path home;

	@Autowired
	public ArchivoServiceImpl(final Path home, final ArchivoRepository archivoRepository) {
		this.home = home;
		this.archivoRepository = archivoRepository;
	}

	@Override
	public Archivo crear(byte[] bytes, Excepcion excepcion) {
		Archivo archivo = new Archivo();

		try {

			String nombreArchivo = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + "";
			Path carpeta = home.resolve("carpetaArchivos").resolve("ExcepcionesCreadas");
			Path rutaArchivo = carpeta.resolve(nombreArchivo + ".txt");

			if (!Files.exists(carpeta))
				Files.createDirectories(carpeta);

			if (!Files.exists(rutaArchivo))
				Files.createFile(rutaArchivo);

			Files.write(rutaArchivo, bytes, StandardOpenOption.APPEND);

			archivo.setEstado(EstadoArchivo.LOCAL);
			archivo.setFecha(LocalDateTime.now());
			archivo.setNombre(nombreArchivo);
			archivo.setPath(rutaArchivo.toUri());
			archivo.setExcepcion(excepcion);
			archivoRepository.save(archivo);
			return archivo;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

}
