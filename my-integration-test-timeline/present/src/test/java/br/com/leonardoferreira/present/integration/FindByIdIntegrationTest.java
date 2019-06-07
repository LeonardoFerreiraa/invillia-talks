package br.com.leonardoferreira.present.integration;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;

import br.com.leonardoferreira.present.CleanDatabase;
import br.com.leonardoferreira.present.domain.Contact;
import br.com.leonardoferreira.present.factory.ContactFactory;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("testes para buscar meus contatos por id")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindByIdIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ContactFactory contactFactory;

    @Test
    @DisplayName("Deve buscar contato por id com sucesso.")
    public void findById() {
        Contact contact = contactFactory.create();

        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/contacts/1")
                .then()
                    .log().all()
                    .statusCode(SC_OK)
                    .body("id", Matchers.is(1))
                    .body("name", Matchers.is(contact.getName()))
                    .body("email", Matchers.is(contact.getEmail()));
    }

    @Test
    @DisplayName("Deve buscar contato por id sem sucesso (contato não existe) tururu.")
    public void findByIdNotFound() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/contacts/1")
                .then()
                    .log().all()
                    .statusCode(SC_NOT_FOUND);
    }

}
