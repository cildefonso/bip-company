package com.bip.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bip.api.domain.model.Company;
import com.bip.api.domain.service.CompanyService;
import com.bip.api.domain.service.UfCacheService;
import com.bip.api.dtos.CompanyDto;
import com.bip.api.response.Response;

/* Teste de stress e performace com Apache Ab
   ab -n 10000 -c 100 http://localhost:8080/api/companymongodb/
*/
@RestController
@EnableCaching
@RequestMapping("/api/company")
public class CompanyController {
	
	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@Autowired
	private UfCacheService ufCacheService;
	
	@Autowired
	private CompanyService companyService;

	
	/**
	 * Retorna a listagem de empresas.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<LancamentoDto>>
	 */
	@GetMapping(value = "/listacnpj/{strCnpj}")
	public ResponseEntity<Response<Page<CompanyDto>>> findForCompanyCnpj(
			@PathVariable("strCnpj") String strCnpj,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Buscando empresas por ID do : {}, página: {}", strCnpj, pag);
		Response<Page<CompanyDto>> response = new Response<Page<CompanyDto>>();

		PageRequest pageRequest = new PageRequest(pag, this.qtdPorPagina, Direction.valueOf(dir), ord);
		Page<Company> companies = this.companyService.findForCompanyCnpj(strCnpj, pageRequest);
		Page<CompanyDto> companyDto = companies.map(company -> this.converterCompanyDto(company));

		response.setData(companyDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Converte uma entidade lançamento para seu respectivo DTO.
	 * 
	 * @param lancamento
	 * @return LancamentoDto
	 */
	private CompanyDto converterCompanyDto(Company company) {
		CompanyDto companyDto = new CompanyDto();
//		lancamentoDto.setId(Optional.of(lancamento.getId()));
//		lancamentoDto.setData(this.dateFormat.format(lancamento.getData()));
//		lancamentoDto.setTipo(lancamento.getTipo().toString());
//		lancamentoDto.setDescricao(lancamento.getDescricao());
//		lancamentoDto.setLocalizacao(lancamento.getLocalizacao());
//		lancamentoDto.setFuncionarioId(lancamento.getFuncionario().getId());

		return companyDto;
	}
	
	@GetMapping(value = "/cnpj/{strCnpj}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Company> findByCnpj(@PathVariable ("strCnpj") String strCnpj) {
		System.out.println("--------------------------------");
		Company company = companyService.findByCnpj(strCnpj);
		System.out.println("Informações da empresa "+ company);
		log.info("Informações da empresa "+ company);
		
		System.out.println(this.ufCacheService.lisUfCache());
		if (!(company == null)) {
		   return ResponseEntity.ok(company);
		}
	    
		return ResponseEntity.notFound().build();	 		
	}
	
	@GetMapping(value = "/email/{strEmail}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Company> findByEmail(@PathVariable ("strEmail") String strEmail) {
		System.out.println("--------------------------------");
		Company company = companyService.findByEmail(strEmail);
		System.out.println("Informações da empresa "+ company);
		log.info("Informações da empresa "+ company);
		
		System.out.println(this.ufCacheService.lisUfCache());
		if (!(company == null)) {
		   return ResponseEntity.ok(company);
		}
	    
		return ResponseEntity.notFound().build();	 		
	}
	
	@GetMapping(value = "/", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<List<Company>> findByCnpj() {
		System.out.println("--------------------------------");
		
		List<Company> company = companyService.findAll();
		System.out.println("Informações da empresa "+ company);
		log.info("Lista de todas as empresa "+ company);
		
		System.out.println(this.ufCacheService.lisUfCache());
		if (!(company == null)) {
		   return ResponseEntity.ok(company);
		}
	    
		return ResponseEntity.notFound().build();	 		
	}
	
	@PostMapping(value = "/", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Company> register(@Valid @RequestBody Company company) {
		if ((company == null )) {
			return ResponseEntity.notFound().build();
		}
		Company companyMongoDB = companyService.insert(company);
		
		return ResponseEntity.ok(companyMongoDB);
	}
	
	@PutMapping(value = "/{strCnpj}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Company> change(@Valid @PathVariable ("strCnpj") String strCnpj, @RequestBody Company company){
		
		if ((company == null )) {
			return ResponseEntity.notFound().build();
		}
		//companyMongoDB.setCnpj(strCnpj);
		Company companyMongoDB = companyService.upDate(company);
		
		
		return ResponseEntity.ok(companyMongoDB);
	}
	
	@DeleteMapping(value = "/{strCnpj}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Void> deletar(@PathVariable String strCnpj){
		Company company = companyService.findByCnpj(strCnpj);
		
		if ((company == null)) {
			return ResponseEntity.notFound().build();
			
		}
		companyService.deletar(company);
	
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "cnpj/v4/{strCnpj}", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Company> buscarV1(@PathVariable ("strCnpj") String strCnpj) {
		Company companyMongoDB = new Company();
		//companyMongoDBRepository.deleteAll();
		//companyMongoDBRepository.save(new CompanyMongoDB("Alice", 20));
		//companyMongoDBRepository.save(new CompanyMongoDB("João", 30));
		//companyMongoDBRepository.save(new CompanyMongoDB("Maria", 40));
		companyService.findAll().forEach(System.out::println);
		System.out.println();
	
		System.out.println("--------------------------------");
		companyService.findByNumberAddressBetween(18, 90).forEach(System.out::println);
		System.out.println("--------------------------------");
		
		System.out.println("Executando serviço pela primeira vez: ");
		System.out.println(this.ufCacheService.lisUfCache());
		
		System.out.println("Executando serviço pela segunda vez, deve obter dados do cache: ");
		System.out.println(this.ufCacheService.lisUfCache());

        //int n = companyMongoDBRepository.updateDomain("mkyong.com", true);
        //System.out.println("Number of records updated : " + n);
	    
	    return ResponseEntity.ok(companyMongoDB);	
	}
	
	

}
