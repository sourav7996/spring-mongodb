package com.example.springmongodb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    BookRepository repository;
    public void save(MultipartFile file){
        try{
            List<Book> books = ExcelHelper.excelToBooks(file.getInputStream());
            repository.saveAll(books);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public List<Book> getAllBooks(){
        return repository.findAll();
    }
}
