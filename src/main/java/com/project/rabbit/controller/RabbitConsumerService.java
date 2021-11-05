package com.project.rabbit.controller;

import com.project.rabbit.model.People;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class RabbitConsumerService {

    @RabbitListener(queues = "Mobile")
    public void getMessage(byte[] msg) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(msg);
        ObjectInput in = new ObjectInputStream(bis);
        People p = (People) in.readObject();
        in.close();
        bis.close();
        System.out.println(p.getName());
    }

}
