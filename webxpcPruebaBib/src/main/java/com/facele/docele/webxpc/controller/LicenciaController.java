package com.facele.docele.webxpc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facele.docele.webxpc.domain.Licencia;
import com.facele.docele.webxpc.service.LicenciaService;
import io.facele.facilito.dto.v10.xpc.LicenciaType;
import io.facele.facilito.dto.v10.xpc.OutputLicenciaConsultar;

@RestController

public class LicenciaController {

	private LicenciaService licenciaService;
	
	@Autowired
	public LicenciaController(final LicenciaService licenciaService) {
		this.licenciaService = licenciaService;
	}
	
	@GetMapping("/sa/licencia/v10")
	ResponseEntity<OutputLicenciaConsultar> consultar(
			@RequestParam(name = "descripcion",required = false)final String descripcion,
			@RequestParam(name = "limit", defaultValue = "100")final Integer limit,
			@RequestParam(name = "offset", defaultValue = "0")final Integer offset){
		
		List<Licencia> licencias = licenciaService.consultar(descripcion, limit, offset);
		
		OutputLicenciaConsultar outputDTO = new OutputLicenciaConsultar();
		LicenciaType licenciaType;
				
		for(Licencia licencia : licencias) {
			licenciaType = new LicenciaType();
			licenciaType.setID(licencia.getId());
			licenciaType.setIdentificacion(licencia.getIdentificacion());
			outputDTO.getRegistro().add(licenciaType);
		}		
		return ResponseEntity.ok(outputDTO);
	}
}

