package com.project.rabbit.controller;

import com.project.rabbit.model.People;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumerService {

    @RabbitListener(queues = "Mobile")
    public void getMessage(People p) {
        System.out.println(p.getName());
    }

}
