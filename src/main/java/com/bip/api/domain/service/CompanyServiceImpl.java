package com.bip.api.domain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bip.api.domain.model.Company;
import com.bip.api.domain.repository.CompanyRepository;
import com.bip.domain.exception.NegocioException;

@Service
public class CompanyServiceImpl implements CompanyService {

	private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyRepository companyRepository;
	private Company companyExistente;
	
	
	public Page<Company> findForCompanyCnpj(String cnpj, PageRequest pageRequest) {
		log.info("Buscando lançamentos para o funcionário ID {}", cnpj);
		return this.companyRepository.findForCompanyCnpj(cnpj, pageRequest);
	}
	
	public Company insert(Company company) {
		log.info("Inclusion of companies. ");
		Company companyExistente = companyRepository.findByEmail(company.getEmail());
		if (companyExistente != null && !companyExistente.equals(company)) {
			throw new NegocioException("Esta empresa encontra-se cadastrada. ");
		}
	
		return companyRepository.save(company);
	}
	
	public Company upDate(Company company) {
		log.info("Change of companies. ");
		companyExistente = companyRepository.findByCnpj(company.getCnpj());
		if (companyExistente == null && companyExistente.equals(company)) {
			throw new NegocioException("Esta empresa não está cadastrada. ");
		}
	
		return companyRepository.save(company);
	}
	
   public void deletar(Company company) {
	   log.info("Exclusion of companies ");
	   companyRepository.delete(company);
   }
   
   public List<Company> findAll() {
	  log.info("Consult all companies. ");
      return companyRepository.findAll();
   }

   public long count() {
	   log.info("Total of companies. ");
       return companyRepository.count();
   }

   public Company findByUserId(String userId) {
	   log.info("Consult of the user: {} ", userId);
	   return companyRepository.findByUserId(userId);
   }

   public Company findBy_id(String companyId) {
	   log.info("Consult of company: {} ", companyId);
	      return companyRepository.findBy_id(companyId);
   }
   
   public Company findByCnpj(String cnpj) {
	   log.info("Consult of company for cnpj: {} ", cnpj);
	   return companyRepository.findByCnpj(cnpj);
   }
   
   public Company findByEmail(String email) {
	   log.info("Consult of company for email: {} ", email);
	   return companyRepository.findByEmail(email);
   }
   
   public Company findByfullnamecompany(String fullnamecompany) {
	   log.info("Consult of company for fullname: {} ", fullnamecompany);
	   return companyRepository.findByfullnamecompany(fullnamecompany);
   }
   
   public List<Company> findByNumberAddressBetween(int numberInitial, int numberFinal) {
	   log.info("Consult of company for address: {} ",numberInitial+" "+numberFinal);
	   return companyRepository.findByNumberAddressBetween(numberInitial,numberFinal);
   }


}
