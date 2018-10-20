package com.vvtalico.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vvtalico.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
