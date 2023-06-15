package com.nnamanx.crud.service.impl;

import com.nnamanx.crud.entity.Book;
import com.nnamanx.crud.repository.BookRepository;
import com.nnamanx.crud.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
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

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
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

    @Override
    public ResponseEntity<Book> addBook(Book book) {
        try {
            Book bookObject = bookRepository.save(book);
            return new ResponseEntity<>(bookObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Book> updateBookById(Long id, Book newBookData) {
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

    @Override
    public ResponseEntity<HttpStatus> deleteBookById(Long id) {
        bookRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
