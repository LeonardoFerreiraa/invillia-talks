package com.invillia.feign.controller;

import com.invillia.feign.exception.NotFoundException;
import com.invillia.feign.client.HttpBinClient;
import com.invillia.feign.client.TodoClient;
import com.invillia.feign.domain.Anything;
import com.invillia.feign.domain.Customer;
import com.invillia.feign.domain.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private final HttpBinClient httpBinClient;

    private final TodoClient todoClient;

    public SimpleController(HttpBinClient httpBinClient, TodoClient todoClient) {
        this.httpBinClient = httpBinClient;
        this.todoClient = todoClient;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/todos/{id}")
    public Todo findTodoById(@PathVariable Long id) {
        if (id > 5) {
            throw new NotFoundException();
        }

        return todoClient.findById(id);
    }

    @GetMapping("/ip")
    public String findIp() {
        return httpBinClient.findMyIp()
                .getOrigin();
    }

    @PostMapping("/anything")
    public ResponseEntity<?> anything() {
        Customer customer = new Customer();
        customer.setName("luizão");

        Anything anything = httpBinClient.findAnything(customer);
        System.out.println(anything);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
