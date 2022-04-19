package repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import model.book;


public interface BookRepository extends MongoRepository<book, Integer> {
}
