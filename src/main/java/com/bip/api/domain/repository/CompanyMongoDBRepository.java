package com.bip.api.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bip.api.domain.model.CompanyMongoDB;


// No need implementation, just one interface, and you have CRUD, thanks Spring Data
//https://www.mballem.com/post/nosql-com-mongodb-e-persistencia-em-java/
public interface CompanyMongoDBRepository extends MongoRepository<CompanyMongoDB, String> {

	CompanyMongoDB findByUserId(String userId);
	CompanyMongoDB findBy_id(String userId);
	CompanyMongoDB findByCnpj(String cnpj);
	CompanyMongoDB findByEmail(String email);
	CompanyMongoDB findByfullnamecompany(String fullnamecompany);

	@Query("{ 'numberAddress' : { $gt: ?0, $lt: ?1 } }")
	List<CompanyMongoDB> findByNumberAddressBetween(int numberInitial, int numberFinal);
		//CompanyMongoDB findByDomainAndDisplayAds(String company, boolean displayAds);
	@Query("{_id:'?0'}")
	CompanyMongoDB findCustomByDomain(String company);
	
	//Supports native JSON query string
    //
   // @Query("{company: { $regex: ?0 } })")
    //List<CompanyMongoDB> findCustomByRegExDomain(String company);
    //int updateDomain(String domain, boolean displayAds);
	//List<CompanyMongoDB> findByCnpj(String cnpj);
    
    
}
