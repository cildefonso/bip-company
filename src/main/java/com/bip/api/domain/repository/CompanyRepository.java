package com.bip.api.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bip.api.domain.model.Company;


@Transactional(readOnly = true)
//@NamedQueries({
		//@NamedQuery(name = "CompanyRepository.findForCompanyCnpj", 
				//query = "SELECT comp FROM companies comp WHERE comp.companies.id = :id") })

public interface CompanyRepository extends MongoRepository<Company, String> {

	
	Page<Company> findForCompanyCnpj(@Param("cnpj") String cnpj, Pageable pageable);
	Company findByUserId(String userId);
	Company findBy_id(String userId);
	Company findByCnpj(String cnpj);
	Company findByEmail(String email);
	Company findByfullnamecompany(String fullnamecompany);
	@Query("{ 'numberAddress' : { $gt: ?0, $lt: ?1 } }")
	List<Company> findByNumberAddressBetween(int numberInitial, int numberFinal);
	List<Company> findAll();
}
