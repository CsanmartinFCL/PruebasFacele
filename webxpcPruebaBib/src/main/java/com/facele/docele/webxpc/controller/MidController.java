package com.facele.docele.webxpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.facele.docele.webxpc.domain.Mid;
import com.facele.docele.webxpc.service.MidService;
import io.facele.facilito.dto.v10.xpc.MIDType;
import io.facele.facilito.dto.v10.xpc.OutputMIDConsultar;

@RestController

public class MidController {

	private MidService midService;
	
	@Autowired
	public MidController(final MidService midService) {
		this.midService = midService;
	}
	
	@GetMapping("/sa/mid/v10")
	ResponseEntity<OutputMIDConsultar> consultar(
			@RequestParam(name = "descripcion",required = false)final String descripcion,
			@RequestParam(name = "limit", defaultValue = "100")final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0")final Integer offset){
		
		List<Mid> mids = midService.consultar(descripcion, limit, offset);
		OutputMIDConsultar outputDTO = new OutputMIDConsultar();
		MIDType midType;
				
		for(Mid mid : mids) {
			midType = new MIDType();
			midType.setID(mid.getId());
			midType.setDescripcion(mid.getDescripcion());
			outputDTO.getRegistro().add(midType);
		}		
		return ResponseEntity.ok(outputDTO);
	}
}
