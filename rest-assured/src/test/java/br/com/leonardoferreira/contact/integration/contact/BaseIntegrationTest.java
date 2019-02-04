package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.CleanDatabase;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class BaseIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private CleanDatabase cleanDatabase;

    @BeforeEach
    public void beforeEach() {
        RestAssured.port = port;
        cleanDatabase.clean();
    }

    protected ResponseSpecification notFoundSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectBody("timestamp", Matchers.not(Matchers.isEmptyString()))
                .expectBody("status", Matchers.is(404))
                .expectBody("error", Matchers.is("Not Found"))
                .expectBody("message", Matchers.is("Resource Not Found"))
                .expectBody("path", Matchers.not(Matchers.isEmptyString()))
                .build();
    }

}
