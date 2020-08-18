package com.bip.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.api.domain.model.CompanyMongoDB;
import com.bip.api.domain.repository.CompanyMongoDBRepository;
import com.bip.domain.exception.NegocioException;

@Service
public class CompanyMongoDBService {
	@Autowired
	private CompanyMongoDBRepository companyMongoDBRepository;
	private CompanyMongoDB companyExistente;
	
	public CompanyMongoDB insert(CompanyMongoDB company) {
		CompanyMongoDB companyExistente = companyMongoDBRepository.findByEmail(company.getEmail());
		if (companyExistente != null && !companyExistente.equals(company)) {
			throw new NegocioException("Esta empresa encontra-se cadastrada. ");
		}
	
		return companyMongoDBRepository.save(company);
	}
	
	public CompanyMongoDB upDate(CompanyMongoDB company) {
		companyExistente = companyMongoDBRepository.findByCnpj(company.getCnpj());
		if (companyExistente == null && companyExistente.equals(company)) {
			throw new NegocioException("Esta empresa não está cadastrada. ");
		}
	
		return companyMongoDBRepository.save(company);
	}
	
   public void deletar(CompanyMongoDB company) {
	   companyMongoDBRepository.delete(company);
   }
   
   public List<CompanyMongoDB> findAll() {
      return companyMongoDBRepository.findAll();
   }

   public long count() {
       return companyMongoDBRepository.count();
   }

}
