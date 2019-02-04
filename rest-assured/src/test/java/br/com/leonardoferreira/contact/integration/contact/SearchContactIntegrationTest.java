package br.com.leonardoferreira.contact.integration.contact;

import br.com.leonardoferreira.contact.domain.Contact;
import br.com.leonardoferreira.contact.factory.ContactFactory;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchContactIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ContactFactory contactFactory;

    @Test
//    @DisplayName("testa se retorna todos os contatos")
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
                    .spec(notFoundSpec());
        // @formatter:on
    }

}
