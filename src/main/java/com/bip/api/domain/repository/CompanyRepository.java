package com.bip.api.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bip.api.domain.model.Company;



public interface CompanyRepository extends MongoRepository<Company, String> {

	Company findByUserId(String userId);
	Company findBy_id(String userId);
	Company findByCnpj(String cnpj);
	Company findByEmail(String email);
	Company findByfullnamecompany(String fullnamecompany);
	@Query("{ 'numberAddress' : { $gt: ?0, $lt: ?1 } }")
	List<Company> findByNumberAddressBetween(int numberInitial, int numberFinal);
	List<Company> findAll();
	//CompanyMongoDB findByDomainAndDisplayAds(String company, boolean displayAds);
	//@Query("{_id:'?0'}")
	//Company findCustomByDomain(String company);
	//Supports native JSON query string
    // @Query("{company: { $regex: ?0 } })")
    //List<CompanyMongoDB> findCustomByRegExDomain(String company);
    //int updateDomain(String domain, boolean displayAds);
	//List<CompanyMongoDB> findByCnpj(String cnpj);
    
    
}
