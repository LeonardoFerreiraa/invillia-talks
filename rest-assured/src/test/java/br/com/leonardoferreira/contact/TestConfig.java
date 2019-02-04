package br.com.leonardoferreira.contact;

import com.github.javafaker.Faker;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TestConfig {

    @Bean
    public Faker faker() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        log.info("seed={}", seed);

        return new Faker(random);
    }

}
