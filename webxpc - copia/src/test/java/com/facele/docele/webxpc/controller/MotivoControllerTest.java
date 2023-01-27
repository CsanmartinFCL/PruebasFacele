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
import com.facele.docele.webxpc.domain.Motivo;
import com.facele.docele.webxpc.service.MotivoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.facele.facilito.dto.JAXBUtil;
import io.facele.facilito.dto.v10.xpc.OutputMotivoConsultar;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MotivoController.class)
@AutoConfigureMockMvc(addFilters = false)

public class MotivoControllerTest {

	private ObjectMapper objectMapper;
	private JacksonTester<OutputMotivoConsultar> jsonConsultar;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MotivoService motivoService;

	@BeforeEach
	public void setup() {

		objectMapper = TestDomain.getObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}


	@Test
	public void consultar() throws Exception {

		List<Motivo> response = TestDomain.getListMotivo();

		// given
		given(motivoService.consultar(eq(null), any(Integer.class), any(Integer.class)))
				.willReturn(response);

		// when
		MockHttpServletResponse responseMock = mvc.perform(
				get("/sa/motivo/v10").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		// then
		assertThat(responseMock.getStatus()).isEqualTo(HttpStatus.OK.value());

		OutputMotivoConsultar output = jsonConsultar.parseObject(responseMock.getContentAsString());

		JAXB.marshal(output, System.out);

		JAXBUtil.validarSchema(OutputMotivoConsultar.class, output);

		assertThat(output.getRegistro().size()).isEqualTo(response.size());

	}

}