package com.invillia.feign.client;

import com.invillia.feign.domain.Anything;
import com.invillia.feign.domain.Customer;
import com.invillia.feign.domain.MyIp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "httpBinClient",
        url = "${client.http-bin.url}"
)
public interface HttpBinClient {

    @GetMapping("/ip")
    MyIp findMyIp();

    @PostMapping("/anything")
    Anything findAnything(@RequestBody Customer customer);

}
