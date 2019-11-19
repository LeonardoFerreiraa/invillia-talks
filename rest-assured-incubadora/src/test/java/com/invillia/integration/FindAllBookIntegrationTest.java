package com.invillia.integration;

import static javax.servlet.http.HttpServletResponse.SC_OK;

import com.invillia.factory.BookFactory;
import io.restassured.RestAssured;
import javax.servlet.http.HttpServletResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FindAllBookIntegrationTest {

    private final BookFactory bookFactory;

    @Autowired
    FindAllBookIntegrationTest(final BookFactory bookFactory) {
        this.bookFactory = bookFactory;
    }

    @Test
    void findAllWithSuccessTest() {
        bookFactory.create(10);

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/books")
                .then()
                    .log().all()
                    .statusCode(SC_OK)
                    .body("$", Matchers.hasSize(10));
    }

}
