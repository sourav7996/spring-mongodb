package com.example.springmongodb.api;


import org.springframework.data.mongodb.repository.MongoRepository;



public interface BookRepository extends MongoRepository<Book, Integer> {
}
