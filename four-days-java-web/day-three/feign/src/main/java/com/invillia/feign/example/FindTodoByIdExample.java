package com.invillia.feign.example;

import com.invillia.feign.client.TodoClient;
import com.invillia.feign.domain.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FindTodoByIdExample implements CommandLineRunner {

    private final TodoClient todoClient;

    public FindTodoByIdExample(TodoClient todoClient) {
        this.todoClient = todoClient;
    }

    @Override
    public void run(String... args) throws Exception {
        Todo todo = todoClient.findById(5L);
        System.out.println(todo);
    }
}
