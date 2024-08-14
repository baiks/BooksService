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

    @DisplayName("createUpSuccess")
    @Test
    public void createUpSuccess() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();
        Assertions.assertEquals(booksService.create(signupDto).getStatusCode(), HttpStatus.CREATED);
    }

    @DisplayName("createUpFailed")
    @Test
    public void createUpFailed() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();
        Assertions.assertEquals(booksService.create(signupDto).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

}
