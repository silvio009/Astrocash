package com.example.Astrocash.repository;

import com.example.Astrocash.models.users.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}
