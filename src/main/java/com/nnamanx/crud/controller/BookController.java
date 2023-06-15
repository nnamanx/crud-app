package com.nnamanx.crud.controller;

import com.nnamanx.crud.entity.Book;
import com.nnamanx.crud.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController("/")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> getAllBooks() {

        try {
            List<Book> bookeList = new ArrayList<>();
            bookRepository.findAll().forEach(bookeList::add);

            if (bookeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        try {
            Optional<Book> bookData = bookRepository.findById(id);

            if (bookData.isPresent()) {
                return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/new-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {

            Book bookObject = bookRepository.save(book);
            return new ResponseEntity<>(bookObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/book-update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData) {

        try {
            Optional<Book> oldBookData = bookRepository.findById(id);
            if (oldBookData.isPresent()) {
                Book updatedBookData = oldBookData.get();
                updatedBookData.setTitle(newBookData.getTitle());
                updatedBookData.setAuthor(newBookData.getAuthor());

                //saving db
                Book bookObj = bookRepository.save(updatedBookData);
                return new ResponseEntity<>(bookObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("removing-book/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        bookRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
