package com.nnamanx.crud.controller;

import com.nnamanx.crud.entity.Book;
import com.nnamanx.crud.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> getAllBooks() {

        log.info("GET - /books -> request none");
        return (ResponseEntity<List<Book>>) bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/new-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/book-update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData) {
        return bookService.updateBookById(id, newBookData);

    }

    @DeleteMapping("removing-book/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        return bookService.deleteBookById(id);
    }

}
