package com.facele.docele.webxpc.controller;

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
import com.facele.docele.webxpc.service.LoadService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.facele.facilito.dto.JAXBUtil;
import io.facele.facilito.dto.v10.xpc.InputUploadCrear;
import io.facele.facilito.dto.v10.xpc.OutputUploadCrear;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoadController.class)
@AutoConfigureMockMvc(addFilters = false)

public class LoadControllerTest {

	private ObjectMapper objectMapper;
	private JacksonTester<OutputUploadCrear> jsonCrear;
	private JacksonTester<InputUploadCrear> jsonInputLoadCrear;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private LoadService loadService;

	@BeforeEach
	public void setup() {

		objectMapper = TestDomain.getObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void crear() throws Exception {
		// given
		given(loadService.crear(any(InputUploadCrear.class))).willReturn("excepcion.txt");
		String det = jsonInputLoadCrear.write(TestDomain.getInputUploadCrear()).getJson();

		// when
		@SuppressWarnings("deprecation")
		MockHttpServletResponse responseMock = mvc
				.perform(post("/sa/load/v10").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(det)).andReturn()
				.getResponse();
		// then
		assertThat(responseMock.getStatus()).isEqualTo(HttpStatus.OK.value());

		OutputUploadCrear output = jsonCrear.parseObject(responseMock.getContentAsString());

		JAXB.marshal(output, System.out);

		JAXBUtil.validarSchema(OutputUploadCrear.class, output);
	}

}
