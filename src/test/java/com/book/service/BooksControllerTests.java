package com.book.service;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

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
    @DisplayName("testCreateSuccess")
    public void testCreateSuccess() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/books").content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("testCreateFailed")
    public void testCreateFailed() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .post("/books").content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @DisplayName("testfindAll")
    public void testfindAll() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/books").contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("testfindByIdSuccess")
    public void testfindByIdSuccess() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/books", 1).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }
    @Test
    @DisplayName("testfindByIdSuccess")
    public void testfindByIdFailed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/books", -1).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    @DisplayName("testDelete")
    public void testDelete() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/books", 1).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("testUpdateSuccess")
    public void testUpdateSuccess() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .put("/books",1).content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
    }

    @Test
    @DisplayName("testUpdateFailed")
    public void testUpdateFailed() throws Exception {
        BooksDto signupDto = BooksDto.builder()
                .title(title)
                .author("paul")
                .year(2024)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .put("/books",-1L).content(new Gson().toJson(signupDto)).contentType(MediaType
                        .APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).with(user("admin").password("1234").roles("ADMIN"));;
        mockMvc.perform(request).andExpect(status().isBadRequest()).andReturn();
    }
}
