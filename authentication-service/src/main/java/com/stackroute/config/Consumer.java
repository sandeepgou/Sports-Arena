package com.stackroute.config;

import com.stackroute.entity.User;
import com.stackroute.entity.UserDTO;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.service.AuthenticationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private AuthenticationService userService;

    @RabbitListener(queues="user_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserAlreadyExistsException {

        User user=new User();
        user.setUserEmail(userDto.getUserEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        System.out.println(user);
        userService.saveUser(user);
    }

}
