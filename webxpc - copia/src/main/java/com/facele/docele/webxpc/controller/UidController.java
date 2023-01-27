package com.facele.docele.webxpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.facele.docele.webxpc.domain.Uid;
import com.facele.docele.webxpc.service.UidService;

import io.facele.facilito.dto.v10.xpc.OutputUIDConsultar;
import io.facele.facilito.dto.v10.xpc.UIDType;

@RestController

public class UidController {

	private UidService uidService;
	
	@Autowired
	public UidController(final UidService uidService) {
		this.uidService = uidService;
	}
	
	@GetMapping("/sa/uid/v10")
	ResponseEntity<OutputUIDConsultar> consultar(
			@RequestParam(name = "descripcion",required = false)final String descripcion,
			@RequestParam(name = "limit", defaultValue = "100")final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0")final Integer offset){
		
		List<Uid> uids = uidService.consultar(descripcion, limit, offset);
		
		OutputUIDConsultar outputDTO = new OutputUIDConsultar();
		UIDType uidType;
				
		for(Uid uid : uids) {
			uidType = new UIDType();
			uidType.setID(uid.getId());
			uidType.setDescripcion(uid.getDescripcion());
			outputDTO.getRegistro().add(uidType);
		}		
		return ResponseEntity.ok(outputDTO);
	}
}
