package br.com.leonardoferreira.present;

import com.github.javafaker.Faker;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfiguration {

    @Bean
    public Faker fakerBean() {
        long seed = System.currentTimeMillis();
        System.out.println(seed);
        Random random = new Random(seed);
        return new Faker(random);
    }

}
