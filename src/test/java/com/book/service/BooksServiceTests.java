package com.book.service;

import com.book.service.dtos.BooksDto;
import com.book.service.services.BooksService;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksServiceTests {
    @Autowired
    BooksService booksService;
    private final String title = RandomStringUtils.randomAlphabetic(10);

    @DisplayName("createBookSuccess")
    @Test
    public void createBookSuccess() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();
        Assertions.assertEquals(booksService.create(signupDto).getStatusCode(), HttpStatus.CREATED);
    }

    @DisplayName("createBookFailed")
    @Test
    public void createBookFailed() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();
        Assertions.assertEquals(booksService.create(signupDto).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @DisplayName("findAll")
    @Test
    public void findAll() throws Exception {
        Assertions.assertEquals(booksService.findAll().getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("findById")
    @Test
    public void findById() throws Exception {
        Assertions.assertEquals(booksService.findById(1L).getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("Delete")
    @Test
    public void delete() throws Exception {
        Assertions.assertEquals(booksService.delete(1L).getStatusCode(), HttpStatus.OK);
    }

    @DisplayName("updateBookSuccess")
    @Test
    public void updateBookSuccess() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();
        Assertions.assertEquals(booksService.update(signupDto, 1L).getStatusCode(), HttpStatus.CREATED);
    }

    @DisplayName("createBookFailed")
    @Test
    public void updateBookFailed() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();
        Assertions.assertEquals(booksService.update(signupDto, -1L).getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
