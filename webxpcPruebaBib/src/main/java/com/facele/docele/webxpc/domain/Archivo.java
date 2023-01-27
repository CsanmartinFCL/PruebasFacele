package com.facele.docele.webxpc.domain;

import java.net.URI;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.facele.docele.webxpc.Constantes.EstadoArchivo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Archivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "excepcion_Id")
	private Excepcion excepcion;
	
	private String nombre;
	private URI path;
	private URI url;
	@Enumerated(EnumType.ORDINAL)
	private EstadoArchivo estado;
	
	@Column (name = "fecha_registro")
	private LocalDateTime fecha;
	
}
