package com.bip.api.domain.repository;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data

public class CompanyMongoDBRepositoryImpl { //implements  CompanyMongoDBRepository {

//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public int updateDomain(String company, boolean displayAds) {
//
//        Query query = new Query(Criteria.where("company").is(company));
//        Update update = new Update();
//        update.set("displayAds", displayAds);
//
//        UpdateResult result = mongoTemplate.updateFirst(query, update, CompanyMongoDB.class);
//
//        if(result!=null)
//            return result.hashCode();
//        else
//            return 0;
//
//    }

//	@Override
//	public <S extends CompanyMongoDB> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	
}
