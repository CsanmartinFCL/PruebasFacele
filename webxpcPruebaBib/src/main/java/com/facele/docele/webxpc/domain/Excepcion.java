package com.facele.docele.webxpc.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.facele.facilito.dto.v10.xpc.EstadoExcepcion;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor

public class Excepcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.ORDINAL)
	private EstadoExcepcion estado;
	
	@Column (name = "fecha_evento")
	private LocalDateTime fechaEvento;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Motivo motivo ;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Mid mid;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Sid sid;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Uid uid;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Abonado abonado;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Licencia licencia;
	
	@Column (name = "cantidad_eventos")
	private int cantidadEventos;
	
	@Column (name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Archivo> archivos;
	
	
	
}
