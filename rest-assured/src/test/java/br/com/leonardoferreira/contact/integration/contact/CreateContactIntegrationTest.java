package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.domain.Contact;
import br.com.leonardoferreira.contact.domain.request.ContactRequest;
import br.com.leonardoferreira.contact.exception.ResourceNotFoundException;
import br.com.leonardoferreira.contact.factory.ContactRequestFactory;
import br.com.leonardoferreira.contact.repository.ContactRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateContactIntegrationTest {

    private final ContactRequestFactory contactRequestFactory;

    private final ContactRepository contactRepository;

    @Autowired
    CreateContactIntegrationTest(final ContactRequestFactory contactRequestFactory,
                                 final ContactRepository contactRepository) {
        this.contactRequestFactory = contactRequestFactory;
        this.contactRepository = contactRepository;
    }

    @Test
    void withSuccess() {
        ContactRequest request = contactRequestFactory.build();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/contacts")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .header("Location", Matchers.containsString("/contacts/1"));
        // @formatter:on

        Contact contact = contactRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("Assert contact content",
                () -> Assertions.assertEquals(request.getName(), contact.getName()),
                () -> Assertions.assertEquals(request.getEmail(), contact.getEmail()));
    }

    @Test
    void failInValidations() {
        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(new ContactRequest())
                .when()
                    .post("/contacts")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("email", Matchers.contains("must not be blank"))
                    .body("name", Matchers.contains("must not be blank"));
        // @formatter:on
    }
}
