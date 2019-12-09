package com.invillia.integration;

import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;

import com.invillia.domain.Book;
import com.invillia.domain.request.UpdateBookRequest;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.factory.BookFactory;
import com.invillia.factory.UpdateBookRequestFactory;
import com.invillia.repository.BookRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpdateBookIntegrationTest {

    private final BookFactory bookFactory;

    private final UpdateBookRequestFactory createBookRequestFactory;

    private final BookRepository bookRepository;

    @Autowired
    UpdateBookIntegrationTest(final BookFactory bookFactory,
                              final UpdateBookRequestFactory createBookRequestFactory,
                              final BookRepository bookRepository) {
        this.bookFactory = bookFactory;
        this.createBookRequestFactory = createBookRequestFactory;
        this.bookRepository = bookRepository;
    }

    @Test
    void updateWithSuccessTest() {
        bookFactory.create();
        final UpdateBookRequest request = createBookRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .put("/books/1")
                .then()
                    .log().all()
                    .statusCode(SC_NO_CONTENT);

        final Book book = bookRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("book assert",
                () -> Assertions.assertEquals(request.getTitle(), book.getTitle()),
                () -> Assertions.assertEquals(request.getNumberOfPages(), book.getNumberOfPages()),
                () -> Assertions.assertEquals(request.getAuthor(), book.getAuthor()));
    }

}
