package com.stackroute.config;

import com.stackroute.entity.Email;
import com.stackroute.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues="booking_queue")
    public void getUserDtoFromRabbitMq(Email emailDto) throws Exception
    {
        System.out.println(emailDto.toString());
        emailService.sendEmail(emailDto);
    }
}
