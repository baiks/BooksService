package com.book.service.repos;

import com.book.service.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {
    List<Books> findByTitle(String title);
}
