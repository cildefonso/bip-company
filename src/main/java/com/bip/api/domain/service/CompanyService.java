package com.bip.api.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bip.api.domain.model.Company;



public interface CompanyService {

	/**
	 * Retorna lista das empresas.
	 * 
	 * @param cnpj
	 * @param pageRequest
	 * @return Page<Company>
	 */
	Page<Company> findForCompanyCnpj(String cnpj, PageRequest pageRequest);
	
	/**
	 * Retorna uma lista de empresas
	 * @param company
	 */
	 Company insert(Company company);
	 
	/**
	 * Retorna uma lista de empresas
	 * @param company
	 */
	 Company upDate(Company company);
	 
	/**
	 * Remove um lançamento da base de dados.
	 * @param company
	 */
 	void deletar(Company company);
 	
	/**
	 * Remove um lançamento da base de dados.
	 */
 	long count();
 	
	/**
	 * Retorna uma lista de empresas.
	 */
 	List<Company> findAll();
 	
	/**
	 * Remove um lançamento da base de dados.
	 * @param userId
	 */
 	Company findByUserId(String userId);
 	
	/**
	 * Remove um lançamento da base de dados.
	 * @param userId
	 */
 	Company findBy_id(String userId);
 	
	/**
	 * Remove um lançamento da base de dados.
	 * @param cnpj
	 */
 	Company findByCnpj(String cnpj);
 	
	/**
	 * Remove um lançamento da base de dados.
	 * @param email
	 */
 	Company findByEmail(String email);
 	
	/**
	 * Remove um lançamento da base de dados.
	 * @param fullnamecompany
	 */
 	Company findByfullnamecompany(String fullnamecompany);
 	
	/**
	 * Remove um lançamento da base de dados.
	 * @param numberInitial
	 * @param numberFinal
	 */
 	List<Company> findByNumberAddressBetween(int numberInitial, int numberFinal);


}
