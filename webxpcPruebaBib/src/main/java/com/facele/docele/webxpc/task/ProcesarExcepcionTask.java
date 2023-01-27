package com.facele.docele.webxpc.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.facele.docele.webxpc.service.impl.ExcepcionServiceImpl;

import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;

import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcesarExcepcionTask {

	@Autowired
	ExcepcionServiceImpl excepcionService;

	Path home;

	@Autowired
	public ProcesarExcepcionTask(final Path home) {
		this.home = home;
	}

	@Scheduled(initialDelay = 500, fixedDelay = 5_000)
	public void ProcesarExcepciones() {
		log.info("Inicia task PocesarExcepcion");
		Path carpeta = home.resolve("carpetaArchivos");
		Path carpetaE = home.resolve("carpetaError");
		Path carpetaProcesados = home.resolve("PROCESADOS");
		if (!Files.exists(carpetaE)) {
			try {
				Files.createDirectory(carpetaE);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		if (!Files.exists(carpetaProcesados)) {
			try {
				Files.createDirectory(carpetaProcesados);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		if (!Files.exists(carpeta)) {
			log.info("La carpeta de zip no existe");
			return;
		}

		for (File zip : carpeta.toFile().listFiles()) {
			if (zip.isDirectory())
				continue;
			log.debug("Procesando archivo " + zip.getAbsolutePath());
			if (!zip.getName().toLowerCase().endsWith(".zip")) {
				// hacer algo acá pq esto no es un zip
				log.error("no es zip");
				continue;
			}
			Path carpetaZip = carpeta.resolve(zip.getName().toLowerCase().split("\\.")[0]);
			if (!Files.exists(carpetaZip)) {
				try {
					Files.createDirectories(carpetaZip);
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			} else {
				throw new RuntimeException("Carpeta zip ya existe");
			}

			try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zip))) {

				ZipEntry salida;
				while (null != (salida = zis.getNextEntry())) {
					try (FileOutputStream fos = new FileOutputStream(carpetaZip.resolve(salida.getName()).toFile())) {
						int leer;
						byte[] buffer = new byte[1024];
						while (0 < (leer = zis.read(buffer))) {
							fos.write(buffer, 0, leer);
						}
					} catch (Exception e) {
						throw new RuntimeException(e.getMessage(), e);
					}
				}
			} catch (Exception e) {
				try {
					Files.move(zip.toPath(), carpetaE.resolve(zip.getName()));
					Files.write(carpetaE.resolve(zip.getName() + ".log"), e.getMessage().getBytes(),
							StandardOpenOption.CREATE_NEW);
				} catch (IOException e1) {
					throw new RuntimeException(e1.getMessage(), e1);
				}
				throw new RuntimeException(e.getMessage(), e);
			}

			InputExcepcionCrear input;
			for (File iec : carpetaZip.toFile().listFiles()) {
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(InputExcepcionCrear.class);

					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					input = (InputExcepcionCrear) jaxbUnmarshaller.unmarshal(iec);
				} catch (Exception e) {
					try {
						Files.move(iec.toPath(), carpetaE.resolve(iec.getName()));
						if (e.getMessage() == null)
							Files.write(carpetaE.resolve(iec.getName() + ".log"),
									"El archivo no es del tipo InputExcepcionCrear".getBytes(),
									StandardOpenOption.CREATE_NEW);
						else
							Files.write(carpetaE.resolve(iec.getName() + ".log"), e.getMessage().getBytes(),
									StandardOpenOption.CREATE_NEW);
					} catch (IOException e1) {
						throw new RuntimeException(e1.getMessage(), e1);
					}
					continue;
				}

				try {
					excepcionService.crear(input);
					Files.move(iec.toPath(), carpetaProcesados.resolve(iec.getName()));
				} catch (Exception e) {
					try {
						Files.move(iec.toPath(), carpetaE.resolve(iec.getName()));
						Files.write(carpetaE.resolve(iec.getName() + ".log"), e.getMessage().getBytes(),
								StandardOpenOption.CREATE_NEW);
					} catch (IOException e1) {
						throw new RuntimeException(e1.getMessage(), e1);
					}
				}
			}

			try {
				Files.delete(zip.toPath());
				Files.delete(carpetaZip);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}

		}

		log.info("Termina task PocesarExcepcion");

	}
}