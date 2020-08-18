package com.bip.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bip.api.domain.model.CompanyMongoDB;
import com.bip.api.domain.repository.CompanyMongoDBRepository;
import com.bip.api.domain.service.CompanyMongoDBService;
import com.bip.api.domain.service.UfCacheService;
/* Teste de stress e performace com Apache Ab
   ab -n 10000 -c 100 http://localhost:8080/api/companymongodb/
*/
@RestController
@EnableCaching
@RequestMapping("/api/companymongodb")
public class CompanyMongoDBController {
	
	private static final Logger log = LoggerFactory.getLogger(CompanyMongoDBController.class);
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@Autowired
	private UfCacheService ufCacheService;
	
	@Autowired
	private CompanyMongoDBService companyMongoDBService;
	
	@Autowired
	private CompanyMongoDBRepository companyMongoDBRepository;
	
	
	@GetMapping(value = "/cnpj/{strCnpj}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<CompanyMongoDB> findByCnpj(@PathVariable ("strCnpj") String strCnpj) {
		System.out.println("--------------------------------");
		CompanyMongoDB company = companyMongoDBRepository.findByCnpj(strCnpj);
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
	public ResponseEntity<CompanyMongoDB> findByEmail(@PathVariable ("strEmail") String strEmail) {
		System.out.println("--------------------------------");
		CompanyMongoDB company = companyMongoDBRepository.findByEmail(strEmail);
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
	public ResponseEntity<List<CompanyMongoDB>> findByCnpj() {
		System.out.println("--------------------------------");
		
		List<CompanyMongoDB> company = companyMongoDBRepository.findAll();
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
	public ResponseEntity<CompanyMongoDB> register(@Valid @RequestBody CompanyMongoDB company) {
		if ((company == null )) {
			return ResponseEntity.notFound().build();
		}
		CompanyMongoDB companyMongoDB = companyMongoDBService.insert(company);
		
		return ResponseEntity.ok(companyMongoDB);
	}
	
	@PutMapping(value = "/{strCnpj}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<CompanyMongoDB> change(@Valid @PathVariable ("strCnpj") String strCnpj, @RequestBody CompanyMongoDB company){
		
		if ((company == null )) {
			return ResponseEntity.notFound().build();
		}
		//companyMongoDB.setCnpj(strCnpj);
		CompanyMongoDB companyMongoDB = companyMongoDBService.upDate(company);
		
		
		return ResponseEntity.ok(companyMongoDB);
	}
	
	@DeleteMapping(value = "/{strCnpj}", headers = "X-API-Version=v1", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<Void> deletar(@PathVariable String strCnpj){
		CompanyMongoDB company = companyMongoDBRepository.findByCnpj(strCnpj);
		
		if ((company == null)) {
			return ResponseEntity.notFound().build();
			
		}
		companyMongoDBService.deletar(company);
	
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "cnpj/v4/{strCnpj}", produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
	public ResponseEntity<CompanyMongoDB> buscarV1(@PathVariable ("strCnpj") String strCnpj) {
		CompanyMongoDB companyMongoDB = new CompanyMongoDB();
		//companyMongoDBRepository.deleteAll();
		//companyMongoDBRepository.save(new CompanyMongoDB("Alice", 20));
		//companyMongoDBRepository.save(new CompanyMongoDB("João", 30));
		//companyMongoDBRepository.save(new CompanyMongoDB("Maria", 40));
		companyMongoDBRepository.findAll().forEach(System.out::println);
		System.out.println();
	
		System.out.println("--------------------------------");
		companyMongoDBRepository.findByNumberAddressBetween(18, 90).forEach(System.out::println);
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
