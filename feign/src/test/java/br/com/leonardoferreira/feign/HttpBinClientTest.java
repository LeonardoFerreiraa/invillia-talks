package br.com.leonardoferreira.feign;

import br.com.leonardoferreira.feign.client.HttpBinClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpBinClientTest {

    @Autowired
    private HttpBinClient httpBinClient;

    @Test
    void anything() {
        Mono<String> anything = httpBinClient.anything();
        anything.subscribe(System.out::println);

        StepVerifier.create(anything)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void ip() {
        Mono<String> result = httpBinClient.ip();
        result.subscribe(System.out::println);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }
}
