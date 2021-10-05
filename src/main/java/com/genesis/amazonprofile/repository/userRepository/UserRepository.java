package com.genesis.amazonprofile.repository.userRepository;

import com.genesis.amazonprofile.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}
