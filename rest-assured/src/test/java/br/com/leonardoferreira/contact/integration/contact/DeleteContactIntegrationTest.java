package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.factory.ContactFactory;
import br.com.leonardoferreira.contact.repository.ContactRepository;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteContactIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ContactFactory contactFactory;

    @Autowired
    private ContactRepository contactRepository;

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
                    .spec(notFoundSpec());
        // @formatter:on
    }
}
