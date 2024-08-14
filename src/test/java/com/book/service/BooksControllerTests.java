package com.book.service;

import com.book.service.dtos.BooksDto;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private final String title = RandomStringUtils.randomAlphabetic(10);

    @Test
    @DisplayName("testCreatSuccess")
    public void testSignUpSuccess() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/books").content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("testCreatFailed")
    public void testCreateFailed() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/books").content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
    }
}
