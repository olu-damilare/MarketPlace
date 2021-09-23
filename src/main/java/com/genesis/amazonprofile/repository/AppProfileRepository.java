package com.genesis.amazonprofile.repository;

import com.genesis.amazonprofile.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppProfileRepository extends MongoRepository<Profile, String> {

}
