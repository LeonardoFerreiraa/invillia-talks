package com.invillia;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

public class Response {

    public static ResponseSpecification notFound() {
        return new ResponseSpecBuilder()
                .expectStatusCode(SC_NOT_FOUND)
                .expectBody("timestamp", Matchers.not(Matchers.empty()))
                .expectBody("status", Matchers.is(404))
                .expectBody("error", Matchers.is("Not Found"))
                .expectBody("message", Matchers.is("No message available"))
                .expectBody("path", Matchers.not(Matchers.empty()))
                .build();
    }

}
