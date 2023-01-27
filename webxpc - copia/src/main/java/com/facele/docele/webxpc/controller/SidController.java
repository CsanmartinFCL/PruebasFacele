package com.facele.docele.webxpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.facele.docele.webxpc.domain.Sid;
import com.facele.docele.webxpc.service.SidService;
import io.facele.facilito.dto.v10.xpc.OutputSIDConsultar;
import io.facele.facilito.dto.v10.xpc.SIDType;

@RestController

public class SidController {

	private SidService sidService;
	
	@Autowired
	public SidController(final SidService sidService) {
		this.sidService = sidService;
	}
	
	@GetMapping("/sa/sid/v10")
	ResponseEntity<OutputSIDConsultar> consultar(
			@RequestParam(name = "descripcion",required = false)final String descripcion,
			@RequestParam(name = "limit", defaultValue = "100")final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0")final Integer offset){
		
		List<Sid> sids = sidService.consultar(descripcion, limit, offset);
		
		OutputSIDConsultar outputDTO = new OutputSIDConsultar();
		SIDType sidType;
				
		for(Sid sid : sids) {
			sidType = new SIDType();
			sidType.setID(sid.getId());
			sidType.setDescripcion(sid.getDescripcion());
			outputDTO.getRegistro().add(sidType);
		}		
		return ResponseEntity.ok(outputDTO);
	}
}
