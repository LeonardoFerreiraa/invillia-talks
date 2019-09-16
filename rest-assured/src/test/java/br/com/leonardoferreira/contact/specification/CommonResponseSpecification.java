package br.com.leonardoferreira.contact.specification;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponseSpecification {

    public static ResponseSpecification notFound() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectBody("timestamp", Matchers.not(Matchers.isEmptyString()))
                .expectBody("status", Matchers.is(404))
                .expectBody("error", Matchers.is("Not Found"))
                .expectBody("message", Matchers.is("Resource Not Found"))
                .expectBody("path", Matchers.not(Matchers.isEmptyString()))
                .build();
    }

}
