package com.invillia.feign.example;

import com.invillia.feign.client.HttpBinClient;
import com.invillia.feign.domain.MyIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FindIpExample implements CommandLineRunner {

    private final HttpBinClient httpBinClient;

    public FindIpExample(HttpBinClient httpBinClient) {
        this.httpBinClient = httpBinClient;
    }

    @Override
    public void run(String... args) throws Exception {
        MyIp myIp = httpBinClient.findMyIp();
        System.out.println(myIp.getOrigin());
    }

}
