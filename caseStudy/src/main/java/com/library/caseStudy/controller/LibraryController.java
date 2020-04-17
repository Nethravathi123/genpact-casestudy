package com.library.caseStudy.controller;

import com.library.caseStudy.dao.BookEntity;
import com.library.caseStudy.dao.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class LibraryController {


    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping(path="/", produces = "application/json")
    public List<BookEntity> getBooks()
    {
        return libraryRepository.findAll();
    }


    @GetMapping(path = "/getBook/{id}", produces = "application/json")
    public Optional<BookEntity> getBook(@PathVariable("id") String id){
        Long bookId = Long.parseLong(id);
        Optional<BookEntity> book = libraryRepository.findById(bookId);
        return book;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path= "/addBooks", consumes = "application/json", produces = "application/json")
    public BookEntity addBooks(@RequestBody BookEntity book)
    {
        System.out.println("book fron UI" + book + "name   " +  book.getName() + "author" + book.getAuthor());
      return  libraryRepository.save(book);
    }

//    @PostMapping(path= "/updateBook/{id}", consumes = "application/json", produces = "application/json")
//    public BookEntity addBooks(@PathVariable("id") String id, @RequestBody BookEntity book)
//    {
//        Long bookId = Long.parseLong(id);
//        Optional<BookEntity> bookEntity = libraryRepository.findById(bookId);
//        if(bookEntity.isPresent()){
//            bookEntity.get().setId(bookId);
//            bookEntity.get().setName(book.getName());
//            bookEntity.get().setAuthor(book.getAuthor());
//        }
//        return  libraryRepository.save(bookEntity);
//    }
}
