package com.example.demo.Repositories;

import com.example.demo.Collections.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin , String> {
}
