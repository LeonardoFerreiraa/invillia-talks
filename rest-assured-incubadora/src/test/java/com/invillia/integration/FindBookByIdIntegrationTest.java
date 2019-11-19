package com.invillia.integration;

import static javax.servlet.http.HttpServletResponse.SC_OK;

import com.invillia.Response;
import com.invillia.domain.Book;
import com.invillia.factory.BookFactory;
import io.restassured.RestAssured;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindBookByIdIntegrationTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final BookFactory bookFactory;

    @Autowired
    FindBookByIdIntegrationTest(final BookFactory bookFactory) {
        this.bookFactory = bookFactory;
    }

    @Test
    void findByIdWithSuccess() {
        final Book book = bookFactory.create();

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/books/1")
                .then()
                    .log().all()
                    .statusCode(SC_OK)
                    .body("id", Matchers.is(1))
                    .body("title", Matchers.is(book.getTitle()))
                    .body("numberOfPages", Matchers.is(book.getNumberOfPages()))
                    .body("isbn", Matchers.is(book.getIsbn()))
                    .body("author", Matchers.is(book.getAuthor()))
                    .body("createdAt", Matchers.is(book.getCreatedAt().format(FORMATTER)))
                    .body("updatedAt", Matchers.is(book.getUpdatedAt().format(FORMATTER)));
    }

    @Test
    void findByIdNotFound() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/books/1")
                .then()
                    .log().all()
                    .specification(Response.notFound());
    }


}
