package com.invillia.integration;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

import com.invillia.domain.Book;
import com.invillia.domain.request.BookRequest;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.factory.BookRequestFactory;
import com.invillia.repository.BookRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateBookIntegrationTest {

    private final BookRepository bookRepository;

    private final BookRequestFactory bookRequestFactory;

    @Autowired
    CreateBookIntegrationTest(final BookRepository bookRepository,
                              final BookRequestFactory bookRequestFactory) {
        this.bookRepository = bookRepository;
        this.bookRequestFactory = bookRequestFactory;
    }

    @Test
    void createBookWithSuccessTest() {
        final BookRequest bookRequest = bookRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(bookRequest)
                .when()
                    .post("/books")
                .then()
                    .log().all()
                    .statusCode(SC_CREATED)
                    .header("Location", Matchers.endsWith("/books/1"));

        Assertions.assertEquals(1, bookRepository.count());

        final Book book = bookRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("book assert",
                () -> Assertions.assertEquals(bookRequest.getTitle(), book.getTitle()),
                () -> Assertions.assertEquals(bookRequest.getNumberOfPages(), book.getNumberOfPages()),
                () -> Assertions.assertEquals(bookRequest.getIsbn(), book.getIsbn()),
                () -> Assertions.assertEquals(bookRequest.getAuthor(), book.getAuthor()));
    }

    @Test
    void createBookWithoutSuccessTest() {
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(new BookRequest())
                .when()
                    .post("/books")
                .then()
                    .log().all()
                    .statusCode(SC_BAD_REQUEST)
                    .body("numberOfPages", Matchers.contains("must not be null"))
                    .body("author", Matchers.contains("must not be blank"))
                    .body("isbn", Matchers.contains("must not be blank"))
                    .body("title", Matchers.contains("must not be blank"));
    }
}
