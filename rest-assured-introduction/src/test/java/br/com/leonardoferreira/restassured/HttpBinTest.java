package br.com.leonardoferreira.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class HttpBinTest {

    @Test
    void accessAnything() {

        ExampleRequest exampleRequest = new ExampleRequest();
        exampleRequest.setName("fuqbbfuqwufhq");
        exampleRequest.setTeste("etset");

        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(exampleRequest)
                .when()
                    .get("https://httpbin.org/anything")
                .then()
                    .log().all()
                    .statusCode(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .body("headers.Accept", Matchers.is("*/*"))
                    .body("json.name", Matchers.is(exampleRequest.getName()))
                    .body("json.teste", Matchers.is(exampleRequest.getTeste()));
        // @formatter:on
    }
}
