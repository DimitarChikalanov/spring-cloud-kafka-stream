package com.example.consumer;

import com.example.consumer.domain.entity.User;
import com.example.consumer.domain.model.UserModel;
import com.example.consumer.repository.UserRepository;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class ConsumerApplication {

    private final UserRepository userRepository;

    public ConsumerApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }


    @Bean
    public Consumer<KStream<String, UserModel>> onUserRegistered() {

        return input -> input.foreach((k, V) -> {
            User user = new User();
            user.setName(V.getName());
            this.userRepository.saveAndFlush(user);
        });
    }
}
