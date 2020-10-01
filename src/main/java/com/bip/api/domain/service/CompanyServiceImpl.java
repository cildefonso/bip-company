package com.bip.api.domain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.api.domain.model.Company;
import com.bip.api.domain.repository.CompanyRepository;
import com.bip.domain.exception.NegocioException;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyRepository companyRepository;
	private Company companyExistente;
	
	public Company insert(Company company) {
		Company companyExistente = companyRepository.findByEmail(company.getEmail());
		if (companyExistente != null && !companyExistente.equals(company)) {
			throw new NegocioException("Esta empresa encontra-se cadastrada. ");
		}
	
		return companyRepository.save(company);
	}
	
	public Company upDate(Company company) {
		companyExistente = companyRepository.findByCnpj(company.getCnpj());
		if (companyExistente == null && companyExistente.equals(company)) {
			throw new NegocioException("Esta empresa não está cadastrada. ");
		}
	
		return companyRepository.save(company);
	}
	
   public void deletar(Company company) {
	   companyRepository.delete(company);
   }
   
   public List<Company> findAll() {
	  log.info("Buscar todas empresas. ");
      return companyRepository.findAll();
   }

   public long count() {
       return companyRepository.count();
   }

   public Company findByUserId(String userId) {
	   log.info("Buscar empresa. ", userId);
	      return companyRepository.findByUserId(userId);
   }

   public Company findBy_id(String companyId) {
	   log.info("Buscar empresa. ", companyId);
	      return companyRepository.findBy_id(companyId);
   }
   
   public Company findByCnpj(String cnpj) {
	   log.info("Buscar empresa pelo cnpj. ", cnpj);
	      return companyRepository.findByCnpj(cnpj);
   }
   
   public Company findByEmail(String email) {
	   log.info("Buscar empresa pelo email. ", email);
	      return companyRepository.findByEmail(email);
   }
   
   public Company findByfullnamecompany(String fullnamecompany) {
	   log.info("Buscar empresa pelo email. ", fullnamecompany);
	      return companyRepository.findByfullnamecompany(fullnamecompany);
   }
   
   public List<Company> findByNumberAddressBetween(int numberInitial, int numberFinal) {
	   log.info("Buscar endereço da empresa. ");
	      return companyRepository.findByNumberAddressBetween(numberInitial,numberFinal);
   }


}
