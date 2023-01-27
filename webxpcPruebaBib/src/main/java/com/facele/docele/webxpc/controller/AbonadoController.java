package com.facele.docele.webxpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.service.AbonadoService;
import com.facele.docele.webxpc.util.LinksPaginado;

import io.facele.facilito.dto.v10.xpc.AbonadoType;
import io.facele.facilito.dto.v10.xpc.EstadoAbonado;
import io.facele.facilito.dto.v10.xpc.OutputAbonadoConsultar;

@RestController

public class AbonadoController {

	private AbonadoService abonadoService;
	
	@Autowired
	public AbonadoController(final AbonadoService abonadoService) {
		this.abonadoService = abonadoService;
	}
	
	@GetMapping("/sa/abonado/v10")
	ResponseEntity<OutputAbonadoConsultar> consultar(
			@RequestParam(name = "estado",required = false)final EstadoAbonado estado,
			@RequestParam(name = "nombre",required = false)final String nombre,
			@RequestParam(name = "abonadoIdentificacion", required = false)final String identificacion,
			@RequestParam(name = "limit", defaultValue = "100")final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0")final Integer offset){
		
		List<Abonado> abonados = abonadoService.consultar(estado, nombre, identificacion, limit, offset);
		
		OutputAbonadoConsultar outputDTO = new OutputAbonadoConsultar();
		AbonadoType abonadoType;
				
		for(Abonado abonado : abonados) {
			abonadoType = new AbonadoType();
			abonadoType.setID(abonado.getId());
			abonadoType.setEstado(abonado.getEstado());
			abonadoType.setNombre(abonado.getNombre());
			abonadoType.setIdentificacion(abonado.getIdentificacion());
			outputDTO.getRegistro().add(abonadoType);
		}
		
		outputDTO.getLink().addAll(LinksPaginado.newInstancia().setPaginado(outputDTO.getRegistro().size(),ServletUriComponentsBuilder.fromCurrentRequest()));
				
;		return ResponseEntity.ok(outputDTO);
	}
	
	
	
	
}
