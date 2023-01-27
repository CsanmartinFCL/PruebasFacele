package com.facele.docele.webxpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facele.docele.webxpc.domain.Motivo;
import com.facele.docele.webxpc.service.MotivoService;
import io.facele.facilito.dto.v10.xpc.MotivoType;
import io.facele.facilito.dto.v10.xpc.OutputMotivoConsultar;

@RestController

public class MotivoController {

	private MotivoService motivoService;
	
	@Autowired
	public MotivoController(final MotivoService motivoService) {
		this.motivoService = motivoService;
	}
	
	@GetMapping("/sa/motivo/v10")
	ResponseEntity<OutputMotivoConsultar> consultar(
			@RequestParam(name = "descripcion",required = false)final String descripcion,
			@RequestParam(name = "limit", defaultValue = "100")final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0")final Integer offset){
		
		List<Motivo> motivos = motivoService.consultar(descripcion, limit, offset);
		
		OutputMotivoConsultar outputDTO = new OutputMotivoConsultar();
		MotivoType motivoType;
				
		for(Motivo motivo : motivos) {
			motivoType = new MotivoType();
			motivoType.setID(motivo.getId());
			motivoType.setDescripcion(motivo.getDescripcion());
			outputDTO.getRegistro().add(motivoType);
		}		
		return ResponseEntity.ok(outputDTO);
	}
}
