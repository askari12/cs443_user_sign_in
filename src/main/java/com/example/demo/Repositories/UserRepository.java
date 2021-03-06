package com.example.demo.Repositories;

import com.example.demo.Collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, CustomQueries {
    @Transactional
    void updateUser(String id, User user);
}