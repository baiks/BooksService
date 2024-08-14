package com.book.service.services;


import com.book.service.dtos.BooksDto;
import com.book.service.entities.Books;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BooksService {
    ResponseEntity<Books> create(BooksDto booksDto);
    ResponseEntity<List<Books>> findAll();
    ResponseEntity<Books> findById(Long id);
    ResponseEntity<Books> update(BooksDto booksDto, Long id);
    ResponseEntity<Boolean> delete(Long id);
}
