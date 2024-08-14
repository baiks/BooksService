package com.book.service.repos;

import com.book.service.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepo extends JpaRepository<Books, Long> {
    Optional<Books> findByTitle(String title);
}
