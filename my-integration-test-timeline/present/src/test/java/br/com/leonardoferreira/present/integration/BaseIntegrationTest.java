package br.com.leonardoferreira.present.integration;

import br.com.leonardoferreira.present.CleanDatabase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class BaseIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private CleanDatabase cleanDatabase;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        cleanDatabase.clean();
    }

}
