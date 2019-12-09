package com.invillia.controller;

import com.invillia.domain.request.CreateBookRequest;
import com.invillia.domain.request.UpdateBookRequest;
import com.invillia.domain.response.BookResponse;
import com.invillia.service.BookService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable final Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public HttpEntity<?> create(@Valid @RequestBody final CreateBookRequest createBookRequest) {
        final Long id = bookService.create(createBookRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/books/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable final Long id,
                                @Valid @RequestBody final UpdateBookRequest updateBookRequest) {
        bookService.update(id, updateBookRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable final Long id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
