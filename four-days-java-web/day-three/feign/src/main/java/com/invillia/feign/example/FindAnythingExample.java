package com.invillia.feign.example;

import com.invillia.feign.client.HttpBinClient;
import com.invillia.feign.domain.Anything;
import com.invillia.feign.domain.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FindAnythingExample implements CommandLineRunner {

    private final HttpBinClient httpBinClient;

    public FindAnythingExample(HttpBinClient httpBinClient) {
        this.httpBinClient = httpBinClient;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setName("Filzão");

        Anything anything = httpBinClient.findAnything(customer);
        System.out.println(anything);
    }
}
