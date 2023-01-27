package com.facele.docele.webxpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facele.docele.webxpc.service.LoadService;

import io.facele.facilito.dto.v10.xpc.InputUploadCrear;
import io.facele.facilito.dto.v10.xpc.OutputUploadCrear;

@RestController
@RequestMapping("/sa/load/v10")

public class LoadController {

	private LoadService loadService;
	
	@Autowired
	public LoadController(final LoadService loadService) {
		this.loadService = loadService;
	}	
	
	@PostMapping
	ResponseEntity<OutputUploadCrear> crear(
			final @RequestBody InputUploadCrear inputDTO){
		OutputUploadCrear outputDTO = new OutputUploadCrear();
		String nombreArchivo = loadService.crear(inputDTO);
		outputDTO.setNombreFile(nombreArchivo);
		return ResponseEntity.ok(outputDTO);
	}
}
