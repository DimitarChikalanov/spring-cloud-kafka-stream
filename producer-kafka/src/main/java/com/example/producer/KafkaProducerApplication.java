package com.example.producer;

import com.example.producer.model.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;


@SpringBootApplication

public class KafkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);

    }

    @Bean
    public Supplier<Flux<UserModel>> publishUser() {
        return () -> Flux.just(new UserModel());
    }
}
