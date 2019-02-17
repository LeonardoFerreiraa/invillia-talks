package br.com.leonardoferreira.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@FeignClient(name = "httpbin", url = "https://httpbin.org")
public interface HttpBinClient {

    @GetMapping("/anything")
    Mono<String> anything();

    @GetMapping("/ip")
    Mono<String> ip();

}
