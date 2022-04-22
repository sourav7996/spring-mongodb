package com.example.springmongodb.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        repository.save(book);
        return "Added Book with id: "+book.getId();
    }

    @GetMapping("/findAllBooks")
    public List<Book> getBooks(){
        return repository.findAll();
    }


    @GetMapping("/findAllBooks/{id}")
    public Optional<Book> getBooks(@PathVariable int id){
        return repository.findById(id);
    }

    @PutMapping("/update")
    public Book updateBooks(@RequestBody Book book){
       Book oldBook= repository.findById(book.getId()).get();
       oldBook.setBookName(book.getBookName());
       oldBook.setAuthorName(book.getAuthorName());
       repository.save(oldBook);
       return oldBook;
//        repository.save(book); Unethical
//        return book; Unethical
    }
    @DeleteMapping("delete/{id}")
    public String deleteBook(@PathVariable int id){
        repository.deleteById(id);
        return "Book with id: "+id +"has been deleted.";
    }
}
