package com.invillia.integration;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

import com.invillia.domain.Book;
import com.invillia.domain.request.CreateBookRequest;
import com.invillia.exception.ResourceNotFoundException;
import com.invillia.factory.BookFactory;
import com.invillia.factory.CreateBookRequestFactory;
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

    private final CreateBookRequestFactory createBookRequestFactory;

    private final BookFactory bookFactory;

    @Autowired
    CreateBookIntegrationTest(final BookRepository bookRepository,
                              final CreateBookRequestFactory createBookRequestFactory,
                              final BookFactory bookFactory) {
        this.bookRepository = bookRepository;
        this.createBookRequestFactory = createBookRequestFactory;
        this.bookFactory = bookFactory;
    }

    @Test
    void createBookWithSuccessTest() {
        final CreateBookRequest createBookRequest = createBookRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(createBookRequest)
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
                () -> Assertions.assertEquals(createBookRequest.getTitle(), book.getTitle()),
                () -> Assertions.assertEquals(createBookRequest.getNumberOfPages(), book.getNumberOfPages()),
                () -> Assertions.assertEquals(createBookRequest.getIsbn(), book.getIsbn()),
                () -> Assertions.assertEquals(createBookRequest.getAuthor(), book.getAuthor()));
    }

    @Test
    void createBookWithoutSuccessTest() {
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(new CreateBookRequest())
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

    @Test
    void createBookWithIsbnAlreadyInUse() {
        final Book book = bookFactory.create();
        final CreateBookRequest request = createBookRequestFactory.build(example ->
                example.setIsbn(book.getIsbn())
        );

        RestAssured
            .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
            .when()
                .post("/books")
            .then()
                .log().all()
                .statusCode(422)
                .body("message", Matchers.is("ISBN already in use"));

    }
}
