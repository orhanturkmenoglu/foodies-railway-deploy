package com.example.foodis.api.repository;

import com.example.foodis.api.entity.ContactEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<ContactEntity,String> {
}
