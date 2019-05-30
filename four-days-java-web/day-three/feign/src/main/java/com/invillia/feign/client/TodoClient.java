package com.invillia.feign.client;

import com.invillia.feign.domain.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "JsonPlaceholderClient", url = "${client.jsonplaceholder.url}")
public interface TodoClient {

    @GetMapping("/todos/{id}")
    Todo findById(@PathVariable("id") Long id);

}
