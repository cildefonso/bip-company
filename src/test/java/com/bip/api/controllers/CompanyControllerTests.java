package com.bip.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bip.api.domain.model.Company;
import com.bip.api.domain.model.Phones;
import com.bip.api.domain.repository.CompanyRepository;
import com.bip.api.domain.service.CompanyService;
import com.bip.api.dtos.CompanyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerTests {
    //java -jar target/nomedojar.jar
	//java -jar -Dspring.profiles.active=test target/nomedojar.jar
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CompanyRepository companyRepository;
	
	@MockBean
	private CompanyService companyService;
	
	private static final String URL_BASE = "/api/company/";
	private static final String NM_COMPANY = "Private All Employees";
	private static final String NM_EMAIL = "john.bridge@privateallemployees.com";
	//private static final Long ID_COMPANY = 1L;
	private static final Long ID_CNPJ = 1L;
	private static final Integer ID_NUMBER = 1;
	private static final Long ID_COMPLEMENT = 1L;
	private static final Long ID_MAIN = 1L;
	private static final Long ID_SECUNDARY = 1L;
	private static final Long ID_USER = 1L;
	private ObjectId id = new ObjectId();
	
	private static final Date DATA = new Date();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	@Test
	public void contextLoads() {
	}
	
	
	@Test
	@WithMockUser
	public void testRegister() throws Exception {
		Company company = obterDadosCompany();

		BDDMockito.given(this.companyService.insert(Mockito.any(Company.class))).willReturn(company);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data._id").value(id))
				.andExpect(jsonPath("$.data.fullnamecompany").value(NM_COMPANY))
				.andExpect(jsonPath("$.data.email").value(NM_EMAIL))
				.andExpect(jsonPath("$.data.cnpj").value(String.valueOf(ID_CNPJ)))
				.andExpect(jsonPath("$.data.numberAddress").value(ID_NUMBER))
				.andExpect(jsonPath("$.data.complementAddress").value(String.valueOf(ID_COMPLEMENT)))
				.andExpect(jsonPath("$.data.userId").value(ID_USER))
				.andExpect(jsonPath("$.errors").isEmpty());

	}
	
	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		CompanyDto companyDto = new CompanyDto();
		
		companyDto.setFullnamecompany(NM_COMPANY);
		companyDto.setEmail(NM_EMAIL);
		companyDto.setCnpj(String.valueOf(ID_CNPJ));
		companyDto.setNumberAddress(ID_NUMBER);
		companyDto.setComplementAddress(String.valueOf(ID_COMPLEMENT));
		companyDto.setUserid(String.valueOf(ID_USER));
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(companyDto);
	}

	private Company obterDadosCompany() {
		Company company = new Company();
		
		company.set_Id(id);
		company.setUserId(String.valueOf(ID_USER));
		company.setFullnamecompany(NM_COMPANY);
		company.setEmail(NM_EMAIL);
		company.setCnpj(String.valueOf(ID_CNPJ));
		company.setNumberAddress(ID_NUMBER);
		company.setComplementAddress(String.valueOf(ID_COMPLEMENT));
		company.setPhones(new Phones());
		company.getPhones().setMain(String.valueOf(ID_MAIN));
		company.getPhones().setSecundary(String.valueOf(ID_SECUNDARY));
	
		return company;
	}	
	
	@Test
	public void testCarregarContextoDeTeste() {
		assertEquals(100, qtdPorPagina);
	}

}
