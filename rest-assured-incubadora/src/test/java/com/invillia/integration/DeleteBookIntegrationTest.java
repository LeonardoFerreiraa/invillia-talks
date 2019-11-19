package com.invillia.integration;

import static org.apache.http.HttpStatus.SC_NO_CONTENT;

import com.invillia.Response;
import com.invillia.factory.BookFactory;
import com.invillia.repository.BookRepository;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeleteBookIntegrationTest {

    private final BookFactory bookFactory;

    private final BookRepository bookRepository;

    @Autowired
    DeleteBookIntegrationTest(final BookFactory bookFactory,
                              final BookRepository bookRepository) {
        this.bookFactory = bookFactory;
        this.bookRepository = bookRepository;
    }

    @Test
    void deleteWithSuccess() {
        bookFactory.create();

        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/books/1")
                .then()
                    .log().all()
                    .statusCode(SC_NO_CONTENT);

        Assertions.assertEquals(0, bookRepository.count());
    }

    @Test
    void deleteByIdNotFoundTest() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/books/1")
                .then()
                    .log().all()
                    .specification(Response.notFound());
    }
}
