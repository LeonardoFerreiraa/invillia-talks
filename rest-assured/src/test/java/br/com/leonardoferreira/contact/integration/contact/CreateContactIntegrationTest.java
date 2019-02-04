package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.Application;
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
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateContactIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ContactRequestFactory contactRequestFactory;

    @Autowired
    private ContactRepository contactRepository;

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
                    .body("errors.find { it.field == 'email' }.defaultMessage", Matchers.is("must not be blank"))
                    .body("errors.find { it.field == 'name' }.defaultMessage", Matchers.is("must not be blank"));
        // @formatter:on
    }
}
