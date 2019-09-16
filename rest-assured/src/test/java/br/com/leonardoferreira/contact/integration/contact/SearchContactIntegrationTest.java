package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.domain.Contact;
import br.com.leonardoferreira.contact.factory.ContactFactory;
import br.com.leonardoferreira.contact.specification.CommonResponseSpecification;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchContactIntegrationTest {

    private final ContactFactory contactFactory;

    @Autowired
    SearchContactIntegrationTest(final ContactFactory contactFactory) {
        this.contactFactory = contactFactory;
    }

    @Test
    void findAll() {
        contactFactory.create(5);

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/contacts")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("$", Matchers.hasSize(5));
        // @formatter:off
    }

    @Test
    void findById() {
        Contact contact = contactFactory.create();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/contacts/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("name", Matchers.is(contact.getName()))
                    .body("email", Matchers.is(contact.getEmail()));
        // @formatter:on
    }

    @Test
    void findByIdNotFound() {
        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/contacts/1")
                .then()
                    .log().all()
                    .spec(CommonResponseSpecification.notFound());
        // @formatter:on
    }

}
