package com.endure.dao;

import com.model.mogodb.UserMongoData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



/**
 * 
 *
 */
@Repository
public interface CaseDao extends MongoRepository<UserMongoData, String> {

}
