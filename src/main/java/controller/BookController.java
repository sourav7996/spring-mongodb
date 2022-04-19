package controller;

import model.book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody book book){
        repository.save(book);
        return "Added Book with id: "+book.getId();
    }

    @GetMapping("/findAllBooks")
    public List<book> getBooks(){
        return repository.findAll();
    }


    @GetMapping("/findAllBooks/{id}")
    public Optional<book> getBooks(@PathVariable int id){
        return repository.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteBook(@PathVariable int id){
        repository.deleteById(id);
        return "Book with id: "+id +"has been deleted.";
    }
}
