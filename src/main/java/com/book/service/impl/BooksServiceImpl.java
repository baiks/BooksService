package com.book.service.impl;

import com.book.service.dtos.BooksDto;
import com.book.service.entities.Books;
import com.book.service.exception.CustomException;
import com.book.service.repos.BooksRepo;
import com.book.service.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {
    private final BooksRepo booksRepo;
    private final ModelMapper modelMapper;

    public ResponseEntity<Books> create(BooksDto booksDto) {
        Optional<Books> res = booksRepo.findByTitle(booksDto.getTitle().toLowerCase());
        if (res.isPresent()) {
            throw new CustomException("A book with the same title exists.");
        }
        Books books = modelMapper.map(booksDto, Books.class);
        booksRepo.save(books);
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Books>> findAll() {
        List<Books> books = booksRepo.findAll();
        if (books.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Books> findById(Long id) {
        Optional<Books> res = booksRepo.findById(id);
        if (res.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(res.get(), HttpStatus.OK);
    }

    public ResponseEntity<Books> update(BooksDto booksDto, Long id) {
        Optional<Books> res = booksRepo.findById(id);
        if (res.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        Books books = modelMapper.map(booksDto, Books.class);
        books.setId(id);
        booksRepo.save(books);
        return new ResponseEntity<>(res.get(), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> delete(Long id) {
        Optional<Books> res = booksRepo.findById(id);
        if (res.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        booksRepo.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
