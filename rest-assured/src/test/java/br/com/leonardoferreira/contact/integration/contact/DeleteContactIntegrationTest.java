package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.factory.ContactFactory;
import br.com.leonardoferreira.contact.repository.ContactRepository;
import br.com.leonardoferreira.contact.specification.CommonResponseSpecification;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeleteContactIntegrationTest {

    private final ContactFactory contactFactory;

    private final ContactRepository contactRepository;

    @Autowired
    DeleteContactIntegrationTest(final ContactFactory contactFactory,
                                 final ContactRepository contactRepository) {
        this.contactFactory = contactFactory;
        this.contactRepository = contactRepository;
    }

    @Test
    void withSuccess() {
        contactFactory.create();

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/contacts/1")
                .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
        // @formatter:on

        Assertions.assertEquals(0, contactRepository.count());
    }

    @Test
    void notFound() {
        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/contacts/1")
                .then()
                    .log().all()
                    .spec(CommonResponseSpecification.notFound());
        // @formatter:on
    }
}
