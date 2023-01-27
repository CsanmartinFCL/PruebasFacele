package com.facele.docele.webxpc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.service.ExcepcionService;
import io.facele.facilito.dto.v10.xpc.AbonadoType;
import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.MIDType;
import io.facele.facilito.dto.v10.xpc.MotivoType;
import io.facele.facilito.dto.v10.xpc.NodoType;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionConsultar;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExceptionObtener;
import io.facele.facilito.dto.v10.xpc.RegistroExcepcionType;
import io.facele.facilito.dto.v10.xpc.SIDType;
import io.facele.facilito.dto.v10.xpc.UIDType;

@RestController
@RequestMapping("/sa/excepcion/v10")

public class ExcepcionController {

	private ExcepcionService excepcionService;

	@Autowired
	public ExcepcionController(final ExcepcionService excepcionService) {
		this.excepcionService = excepcionService;
	}

	@GetMapping
	ResponseEntity<OutputExcepcionConsultar> consultar(
			@RequestParam(name = "motivoId", required = false) final Long motivoId,
			@RequestParam(name = "midId", required = false) final Long midId,
			@RequestParam(name = "sidId", required = false) final Long sidId,
			@RequestParam(name = "abonadoId", required = false) final Long abonadoId,
			@RequestParam(name = "licenciaId", required = false) final Long licenciaId,
			@RequestParam(name = "fechaEventoDesde", required = false) final String fechaEventoDesde,
			@RequestParam(name = "fechaEventoHasta", required = false) final String fechaEventoHasta,
			@RequestParam(name = "limit", defaultValue = "100") final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0") final Integer offset) {

		List<Excepcion> excepcions = excepcionService.consultar(motivoId, midId, sidId, abonadoId, licenciaId,
				fechaEventoDesde, fechaEventoHasta, limit, offset);

		OutputExcepcionConsultar outputDTO = new OutputExcepcionConsultar();
		RegistroExcepcionType excepcionType;
		MotivoType motivoType;
		AbonadoType abonadoType;
		UIDType uidType;
		SIDType sidType;
		MIDType midType;

		for (Excepcion excepcion : excepcions) {

			excepcionType = new RegistroExcepcionType();
			motivoType = new MotivoType();
			abonadoType = new AbonadoType();
			uidType = new UIDType();
			sidType = new SIDType();
			midType = new MIDType();

			excepcionType.setID(excepcion.getId());

			excepcionType.setFechaEvento(excepcion.getFechaEvento().toString());
			excepcionType.setCantidadEventos(excepcion.getCantidadEventos());

			motivoType.setID(excepcion.getMotivo().getId());
			motivoType.setDescripcion(excepcion.getMotivo().getDescripcion());
			excepcionType.setMotivo(motivoType);

			abonadoType.setID(excepcion.getAbonado().getId());
			abonadoType.setEstado(excepcion.getAbonado().getEstado());
			abonadoType.setNombre(excepcion.getAbonado().getNombre());
			abonadoType.setIdentificacion(excepcion.getAbonado().getIdentificacion());
			excepcionType.setAbonado(abonadoType);

			uidType.setID(excepcion.getUid().getId());
			uidType.setDescripcion(excepcion.getUid().getDescripcion());
			excepcionType.setUID(uidType);

			sidType.setID(excepcion.getSid().getId());
			sidType.setDescripcion(excepcion.getSid().getDescripcion());
			excepcionType.setSID(sidType);

			midType.setID(excepcion.getMid().getId());
			midType.setDescripcion(excepcion.getMid().getDescripcion());
			excepcionType.setMID(midType);

			excepcionType.setEstado(excepcion.getEstado().toString());
			outputDTO.getRegistro().add(excepcionType);
		}
		return ResponseEntity.ok(outputDTO);
	}

	@GetMapping("/{id}")
	ResponseEntity<OutputExceptionObtener> obtener(final @PathVariable(name = "id") Long id) {
		try {
			MotivoType motivoType = new MotivoType();
			NodoType nodoType = new NodoType();
			Excepcion excepcion = excepcionService.obetener(id);
			OutputExceptionObtener outputDTO = new OutputExceptionObtener();
			outputDTO.setAbonadoIdentificacion(excepcion.getAbonado().getIdentificacion());
			outputDTO.setFechaEvento(excepcion.getFechaEvento());
			outputDTO.setLicenciaIdentificacion(excepcion.getLicencia().getIdentificacion());
			motivoType.setDescripcion(excepcion.getMotivo().toString());
			motivoType.setID(excepcion.getMotivo().getId());
			outputDTO.setMotivo(motivoType);
			outputDTO.setInput(null);
			nodoType.setMID(excepcion.getMid().getDescripcion());
			nodoType.setSID(excepcion.getSid().getDescripcion());
			nodoType.setUID(excepcion.getUid().getDescripcion());
			outputDTO.setNodo(nodoType);
			outputDTO.setRequestURL("https://localhost:8081/");

			outputDTO.setThrowable(Files.readAllBytes(Paths.get(excepcion.getArchivos().get(0).getPath())));

			outputDTO.setUser(excepcion.getUid().getDescripcion());

			return ResponseEntity.ok(outputDTO);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	@PostMapping
	ResponseEntity<OutputExcepcionCrear> crear(final @RequestBody InputExcepcionCrear inputDTO) {
		OutputExcepcionCrear outputDTO = new OutputExcepcionCrear();
		AbonadoType abonadoType = new AbonadoType();
		Excepcion excepcion = excepcionService.crear(inputDTO);
		MIDType midType = new MIDType();
		MotivoType motivoType = new MotivoType();
		SIDType sidType = new SIDType();
		UIDType uidType = new UIDType();

		abonadoType.setID(excepcion.getAbonado().getId());
		abonadoType.setEstado(excepcion.getAbonado().getEstado());
		abonadoType.setIdentificacion(excepcion.getAbonado().getIdentificacion());
		abonadoType.setNombre(excepcion.getAbonado().getNombre());

		outputDTO.setAbonado(abonadoType);
		outputDTO.setCantidadEventos(1);
		outputDTO.setEstado("");
		outputDTO.setFechaEvento("");
		outputDTO.setID(excepcion.getId());

		midType.setDescripcion(excepcion.getMid().getDescripcion());
		midType.setID(excepcion.getMid().getId());
		outputDTO.setMID(midType);

		motivoType.setDescripcion(excepcion.getMotivo().getDescripcion());
		motivoType.setID(excepcion.getMotivo().getId());
		outputDTO.setMotivo(motivoType);

		sidType.setDescripcion(excepcion.getSid().getDescripcion());
		sidType.setID(excepcion.getSid().getId());
		outputDTO.setSID(sidType);

		uidType.setDescripcion(excepcion.getSid().getDescripcion());
		uidType.setID(excepcion.getSid().getId());
		outputDTO.setUID(uidType);
		return ResponseEntity.ok(outputDTO);
	}
}
