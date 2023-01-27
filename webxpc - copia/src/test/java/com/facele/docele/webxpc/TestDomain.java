package com.facele.docele.webxpc;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.facele.docele.webxpc.Constantes.EstadoArchivo;
import com.facele.docele.webxpc.domain.Abonado;
import com.facele.docele.webxpc.domain.Archivo;
import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.domain.Licencia;
import com.facele.docele.webxpc.domain.Mid;
import com.facele.docele.webxpc.domain.Motivo;
import com.facele.docele.webxpc.domain.Sid;
import com.facele.docele.webxpc.domain.Uid;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.facele.facilito.dto.v10.xpc.EstadoAbonado;
import io.facele.facilito.dto.v10.xpc.EstadoExcepcion;
import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.InputUploadCrear;

public class TestDomain {

	public static ObjectMapper getObjectMapper() {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		objectMapper.registerModule(javaTimeModule);

		return objectMapper;
	}

	public static List<Abonado> getListAbonado() {
		Abonado abonado1 = new Abonado();
		abonado1.setEstado(EstadoAbonado.ACTUALIZADO);
		abonado1.setId((long) 3);
		abonado1.setIdentificacion("parce");
		abonado1.setNombre("Facele");

		Abonado abonado2 = new Abonado();
		abonado2.setEstado(EstadoAbonado.ACTUALIZADO);
		abonado2.setId((long) 2);
		abonado2.setIdentificacion("Parcero");
		abonado2.setNombre("dios");

		Abonado abonado3 = new Abonado();
		abonado3.setEstado(EstadoAbonado.ACTUALIZADO);
		abonado3.setId((long) 1);
		abonado3.setIdentificacion("Par");
		abonado3.setNombre("Tucutucu");

		List<Abonado> response = new ArrayList<>();
		response.add(abonado1);
		response.add(abonado2);
		response.add(abonado3);
		return response;
	}

	public static List<Mid> getListMid() {
		Mid mid1 = new Mid();
		mid1.setId((long) 10);
		mid1.setDescripcion("Mid10");

		Mid mid2 = new Mid();
		mid2.setId((long) 20);
		mid2.setDescripcion("Mid20");

		Mid mid3 = new Mid();
		mid3.setId((long) 30);
		mid3.setDescripcion("Mid30");

		List<Mid> response = new ArrayList<>();
		response.add(mid1);
		response.add(mid2);
		response.add(mid3);
		return response;
	}

	public static List<Sid> getListSid() {
		Sid sid1 = new Sid();
		sid1.setId((long) 10);
		sid1.setDescripcion("Sid10");

		Sid sid2 = new Sid();
		sid2.setId((long) 20);
		sid2.setDescripcion("sid20");

		Sid sid3 = new Sid();
		sid3.setId((long) 30);
		sid3.setDescripcion("Sid30");

		List<Sid> response = new ArrayList<>();
		response.add(sid1);
		response.add(sid2);
		response.add(sid3);
		return response;
	}

	public static List<Uid> getListUid() {
		Uid uid1 = new Uid();
		uid1.setId((long) 10);
		uid1.setDescripcion("Uid10");

		Uid uid2 = new Uid();
		uid2.setId((long) 20);
		uid2.setDescripcion("uid20");

		Uid uid3 = new Uid();
		uid3.setId((long) 30);
		uid3.setDescripcion("Uid30");

		List<Uid> response = new ArrayList<>();
		response.add(uid1);
		response.add(uid2);
		response.add(uid3);
		return response;
	}

	public static List<Motivo> getListMotivo() {
		Motivo motivo1 = new Motivo();
		motivo1.setId((long) 10);
		motivo1.setDescripcion("Motivo10");

		Motivo motivo2 = new Motivo();
		motivo2.setId((long) 20);
		motivo2.setDescripcion("motivo20");

		Motivo motivo3 = new Motivo();
		motivo3.setId((long) 30);
		motivo3.setDescripcion("Motivo30");

		List<Motivo> response = new ArrayList<>();
		response.add(motivo1);
		response.add(motivo2);
		response.add(motivo3);
		return response;
	}

	public static List<Licencia> getListLicencia() {
		Licencia licencia1 = new Licencia();
		licencia1.setId((long) 10);
		licencia1.setIdentificacion("Licencia10");

		Licencia licencia2 = new Licencia();
		licencia2.setId((long) 20);
		licencia2.setIdentificacion("licencia20");

		Licencia licencia3 = new Licencia();
		licencia3.setId((long) 30);
		licencia3.setIdentificacion("Licencia30");

		List<Licencia> response = new ArrayList<>();
		response.add(licencia1);
		response.add(licencia2);
		response.add(licencia3);
		return response;
	}

	public static Motivo getMotivo() {
		Motivo motivo1 = new Motivo();
		motivo1.setDescripcion("Desc1");
		motivo1.setId((long) 1);
		return motivo1;
	}

	public static Abonado getAbonado() {
		Abonado abonado1 = new Abonado();
		abonado1.setEstado(EstadoAbonado.ACTUALIZADO);
		abonado1.setId((long) 3);
		abonado1.setIdentificacion("parce");
		abonado1.setNombre("Facele");
		return abonado1;
	}

	public static Licencia getLicencia() {
		Licencia licencia1 = new Licencia();
		licencia1.setId((long) 10);
		licencia1.setIdentificacion("Licencia10");
		return licencia1;
	}

	public static Mid getMid() {
		Mid mid1 = new Mid();
		mid1.setId((long) 10);
		mid1.setDescripcion("Mid10");
		return mid1;
	}

	public static Sid getSid() {
		Sid sid1 = new Sid();
		sid1.setId((long) 10);
		sid1.setDescripcion("Sid10");
		return sid1;
	}

	public static Uid getUid() {
		Uid uid1 = new Uid();
		uid1.setId((long) 10);
		uid1.setDescripcion("Uid10");
		return uid1;
	}

	
	public static Excepcion getExcepcion() {
		
		Excepcion excepcion1 = new Excepcion();
		
		excepcion1.setAbonado(getAbonado());
		excepcion1.setCantidadEventos(5);
		excepcion1.setEstado(EstadoExcepcion.INFORMADO);
		excepcion1.setFechaEvento(LocalDateTime.now());
		excepcion1.setId((long) 1);
		excepcion1.setLicencia(getLicencia());
		excepcion1.setMid(getMid());
		excepcion1.setSid(getSid());
		excepcion1.setMotivo(getMotivo());
		excepcion1.setUid(getUid());
		
		return excepcion1;
	}	
	
	public static InputExcepcionCrear getInputExcepcionrear() {
		InputExcepcionCrear iec = new InputExcepcionCrear();
		
		iec.setAbonadoIdentificacion("");
		iec.setFechaEvento(LocalDateTime.now());
		iec.setInput("".getBytes());
		iec.setLicenciaIdentificacion("");
		iec.setMotivo(null);
		iec.setNodo(null);
		iec.setRequestURL(null);
		iec.setThrowable(null);
		iec.setUser(null);
		
		return iec;
	}
	
	public static Archivo getArchivo() {
		Archivo archivo = new Archivo();
		archivo.setEstado(EstadoArchivo.LOCAL);
//		archivo.setExcepcion(getExcepcion());
		archivo.setFecha(LocalDateTime.now());
		archivo.setId(1L);
		archivo.setNombre("Test");
		archivo.setPath(Paths.get("src/test/resources/excepcion.txt").toUri());
		archivo.setUrl(null);
		return archivo;
	}
	
	public static List<Excepcion> getListExcepcion() {
		Excepcion excepcion1 = new Excepcion();
		excepcion1.setAbonado(getAbonado());
		excepcion1.setCantidadEventos(5);
		excepcion1.setEstado(EstadoExcepcion.INFORMADO);
		excepcion1.setFechaEvento(LocalDateTime.now());
		excepcion1.setId((long) 1);
		excepcion1.setLicencia(getLicencia());
		excepcion1.setMid(getMid());
		excepcion1.setSid(getSid());
		excepcion1.setMotivo(getMotivo());
		excepcion1.setUid(getUid());
		return Collections.singletonList(excepcion1);
	}
	
	
	public static Archivo getNombre() {
		Archivo nombre = new Archivo();
		nombre.setNombre("Prueba11");
		return nombre;
	}

	public static InputUploadCrear getInputUploadCrear() {
		InputUploadCrear ilc = new InputUploadCrear();
		ilc.setZipBytes("holaMundo".getBytes());
		return ilc;
	}
	
	
}
