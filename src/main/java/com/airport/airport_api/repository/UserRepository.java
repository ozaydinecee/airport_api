package com.airport.airport_api.repository;

import com.airport.airport_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
