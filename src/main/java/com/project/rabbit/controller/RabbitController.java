package com.project.rabbit.controller;

import com.project.rabbit.model.People;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send/{name}")
    public String testAPI(@PathVariable("name") String name) {
        People p = new People(1L, name);
        rabbitTemplate.convertAndSend("Mobile", p);
        return "You message was send with success!";
    }

}
