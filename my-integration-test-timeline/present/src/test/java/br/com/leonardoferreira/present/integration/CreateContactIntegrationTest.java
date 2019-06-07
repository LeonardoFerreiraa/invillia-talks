package br.com.leonardoferreira.present.integration;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

import br.com.leonardoferreira.present.domain.Contact;
import br.com.leonardoferreira.present.domain.request.ContactRequest;
import br.com.leonardoferreira.present.factory.ContactRequestFactory;
import br.com.leonardoferreira.present.repository.ContactRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateContactIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ContactRequestFactory contactRequestFactory;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    @DisplayName("criar com tato com sucesso")
    public void createWithSuccess() {
        ContactRequest request = contactRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/contacts")
                .then()
                    .log().all()
                    .statusCode(SC_CREATED)
                    .header("Location", Matchers.endsWith("/contacts/1"));

        Contact contact = contactRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);
        Assertions.assertAll("contact content",
                () -> Assertions.assertEquals(request.getName(), contact.getName()),
                () -> Assertions.assertEquals(request.getEmail(), contact.getEmail()));
    }

    @Test
    public void failInValidations() {
        ContactRequest request = contactRequestFactory.build("invalid");

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/contacts")
                .then()
                    .log().all()
                    .statusCode(SC_BAD_REQUEST)
                    .body("name", Matchers.containsInAnyOrder("must not be blank"))
                    .body("email", Matchers.containsInAnyOrder("must not be blank"));
    }

}
