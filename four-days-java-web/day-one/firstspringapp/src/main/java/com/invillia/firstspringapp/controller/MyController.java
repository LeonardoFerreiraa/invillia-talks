package com.invillia.firstspringapp.controller;

import com.invillia.firstspringapp.domain.MyEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class MyController {

    @GetMapping
    public MyEntity home(@RequestParam(required = false) String customer) {
        MyEntity myEntity = new MyEntity();
        myEntity.setMessage("lista todo mundo " + customer);

        return myEntity;
    }

    @GetMapping("/{id}")
    public MyEntity home(@PathVariable Integer id) {
        return new MyEntity("ordem por id = " + id);
    }

}

/*
    Bean / Component
        -> Controller
        -> Service
        -> @Repository
 */
