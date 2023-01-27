package com.facele.docele.webxpc.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.facele.facilito.dto.v10.xpc.EstadoAbonado;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Abonado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String identificacion;
	private String nombre;
	@Enumerated(EnumType.ORDINAL)
	private EstadoAbonado estado;
	
}
