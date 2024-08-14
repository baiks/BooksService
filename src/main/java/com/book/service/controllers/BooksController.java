package com.book.service.controllers;

import com.book.service.dtos.BooksDto;
import com.book.service.entities.Books;
import com.book.service.services.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;

    @GetMapping("ping")
    String ping() {
        return "Ping is successful";
    }

    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "Create a book", description = "Returns the details of the book created.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
            "  \"title\": \"Test\",\n" +
            "  \"auth\": \"Paul\",\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /books")})
    public ResponseEntity<Books> create(@Valid @RequestBody BooksDto booksDto) {
        return booksService.create(booksDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "Get all books", description = "Returns the details of all the books.\n" + "\n" + "Example Requests:")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /books")})
    public ResponseEntity<List<Books>> findAll() {
        return booksService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @Operation(summary = "Get all books", description = "Returns the details of a book.\n" + "\n" + "Example Requests:")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /books/1")})
    public ResponseEntity<Books> findById(@Valid @PathVariable Long id) {
        return booksService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @Operation(summary = "Update a book", description = "Returns the details of the book updated.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
            "  \"title\": \"Test\",\n" +
            "  \"auth\": \"Paul\",\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PUT: /books/{id}")})
    public ResponseEntity<Books> update(@Valid @RequestBody BooksDto booksDto, @PathVariable Long id) {
        return booksService.update(booksDto, id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @Operation(summary = "Update a book", description = "Returns the boolena if the book deleted.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n" +
            "  \"title\": \"Test\",\n" +
            "  \"auth\": \"Paul\",\n" +
            "}")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "DELETE: /books/{id}")})
    public ResponseEntity<Boolean> delete(@Valid @RequestBody BooksDto booksDto, @PathVariable Long id) {
        return booksService.delete(id);
    }

}
