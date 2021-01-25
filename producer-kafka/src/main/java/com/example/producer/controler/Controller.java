package com.example.producer.controler;

import com.example.producer.model.UserModel;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t/")
public class Controller {

    private final StreamBridge streamBridge;

    public Controller(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping("u")
    public void sent(@RequestBody UserModel user){
        streamBridge.send("publishUser-out-0",user);
    }
}
