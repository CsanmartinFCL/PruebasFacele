package com.facele.docele.webxpc.controller;

import java.util.List;

import javax.xml.bind.JAXB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.facele.docele.webxpc.TestDomain;
import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.service.ExcepcionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.facele.facilito.dto.JAXBUtil;
import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionConsultar;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionCrear;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ExcepcionController.class)
@AutoConfigureMockMvc(addFilters = false)

public class ExcepcionControllerTest {

	private ObjectMapper objectMapper;
	private JacksonTester<OutputExcepcionConsultar> jsonConsultar;
	private JacksonTester<InputExcepcionCrear> jsonExcepcionCrear;
	private JacksonTester<OutputExcepcionCrear> jsonCrear;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ExcepcionService excepcionService;

	@BeforeEach
	public void setup() {

		objectMapper = TestDomain.getObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void consultar() throws Exception {

		List<Excepcion> response = TestDomain.getListExcepcion();

		// given
		given(excepcionService.consultar(eq(null), eq(null), eq(null), eq(null), eq(null), eq(null), eq(null),
				any(Integer.class), any(Integer.class))).willReturn(response);

		// when
		MockHttpServletResponse responseMock = mvc.perform(get("/sa/excepcion/v10").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		// then
		assertThat(responseMock.getStatus()).isEqualTo(HttpStatus.OK.value());

		OutputExcepcionConsultar output = jsonConsultar.parseObject(responseMock.getContentAsString());

		JAXB.marshal(output, System.out);

		JAXBUtil.validarSchema(OutputExcepcionConsultar.class, output);

		assertThat(output.getRegistro().size()).isEqualTo(response.size());
	}
	
	@Test
	void obtener() throws Exception {
		Excepcion response = TestDomain.getExcepcion();
		
		// given
				given(excepcionService.obetener(any(Long.class))).willReturn(response);

				// when
				MockHttpServletResponse responseMock = mvc.perform(get("/sa/excepcion/v10/1").accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();
				// then
				assertThat(responseMock.getStatus()).isEqualTo(HttpStatus.OK.value());

				OutputExcepcionConsultar output = jsonConsultar.parseObject(responseMock.getContentAsString());

				JAXB.marshal(output, System.out);

				JAXBUtil.validarSchema(OutputExcepcionConsultar.class, output);
	}
	
	@Test
	void crear() throws Exception{
		
		Excepcion response = TestDomain.getExcepcion();
		
		// given
		given(excepcionService.crear(any(InputExcepcionCrear.class))).willReturn(response);
		String det = jsonExcepcionCrear.write(TestDomain.getInputExcepcionrear()).getJson();

		// when
		@SuppressWarnings("deprecation")
		MockHttpServletResponse responseMock = mvc
				.perform(post("/sa/excepcion/v10").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(det)).andReturn()
				.getResponse();
		// then
		assertThat(responseMock.getStatus()).isEqualTo(HttpStatus.OK.value());

		OutputExcepcionCrear output = jsonCrear.parseObject(responseMock.getContentAsString());

		JAXB.marshal(output, System.out);

		JAXBUtil.validarSchema(OutputExcepcionCrear.class, output);
	}
	}
