package com.project.rabbit.controller;

import com.project.rabbit.model.People;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1")
public class RabbitController {

    @Autowired
    RabbitTemplate rabbitTemplate;

//    @GetMapping("/send/{name}")
//    public String testAPI(@PathVariable("name") String name) {
//        People p = new People(1L, name);
//        rabbitTemplate.convertAndSend("Mobile", p);
//        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", p);
//        rabbitTemplate.convertAndSend("Fanout-Exchange", "", p);
//        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", p);
//        return "You message was send with success!";
//    }

    @GetMapping("/send/{name}")
    public String testAPI(@PathVariable("name") String name) throws IOException {
        People p = new People(1L, name);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(p);
        out.flush();
        out.close();

        byte[] byteMessage = bos.toByteArray();
        bos.close();

        Message msg = MessageBuilder.withBody(byteMessage)
                .setHeader("item1", "mobile")
                .setHeader("item2", "television").build();

        rabbitTemplate.send("Headers-Exchange", "", msg);

        return "You message was send with success!";
    }

}
