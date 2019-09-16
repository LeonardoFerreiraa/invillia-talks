package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.domain.Contact;
import br.com.leonardoferreira.contact.domain.request.ContactRequest;
import br.com.leonardoferreira.contact.exception.ResourceNotFoundException;
import br.com.leonardoferreira.contact.factory.ContactFactory;
import br.com.leonardoferreira.contact.factory.ContactRequestFactory;
import br.com.leonardoferreira.contact.repository.ContactRepository;
import br.com.leonardoferreira.contact.specification.CommonResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpdateContactIntegrationTest {

    private final ContactFactory contactFactory;

    private final ContactRequestFactory contactRequestFactory;

    private final ContactRepository contactRepository;

    @Autowired
    UpdateContactIntegrationTest(final ContactFactory contactFactory,
                                 final ContactRequestFactory contactRequestFactory,
                                 final ContactRepository contactRepository) {
        this.contactFactory = contactFactory;
        this.contactRequestFactory = contactRequestFactory;
        this.contactRepository = contactRepository;
    }

    @Test
    void withSuccess() {
        contactFactory.create();
        ContactRequest request = contactRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .put("/contacts/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        // @formatter:on

        Contact contact = contactRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("Assert contact content",
                () -> Assertions.assertEquals(request.getName(), contact.getName()),
                () -> Assertions.assertEquals(request.getEmail(), contact.getEmail()));
    }

    @Test
    void failInValidation() {
        contactFactory.create();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(new ContactRequest())
                .when()
                    .put("/contacts/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("name", Matchers.contains("must not be blank"))
                    .body("email", Matchers.contains("must not be blank"));
        // @formatter:on
    }

    @Test
    void updateNotFound() {
        ContactRequest request = contactRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .put("/contacts/1")
                .then()
                    .log().all()
                    .spec(CommonResponseSpecification.notFound());
        // @formatter:on
    }
}
