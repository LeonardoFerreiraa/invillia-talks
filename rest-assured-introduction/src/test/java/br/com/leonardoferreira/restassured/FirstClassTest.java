package br.com.leonardoferreira.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class FirstClassTest {

    @Test
    void firstTest() {
        System.out.println("ola mundo");
    }

    @Test
    void accessGoogle() {
        // @formatter:off
        RestAssured
                .given()
                    .log().all()
                .when()
                    .get("https://google.com")
                .then()
                    .log().all();
        // @formatter:on
    }

}
