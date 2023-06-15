package com.nnamanx.crud.service;

import com.nnamanx.crud.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<List<Book>> getAllBooks();

    ResponseEntity<Book> getBookById(Long id);

    ResponseEntity<Book> addBook(Book book);

    ResponseEntity<Book> updateBookById(Long id, Book newBookData);

    ResponseEntity<HttpStatus> deleteBookById(Long id);

}