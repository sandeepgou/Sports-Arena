/*
package com.stackroute.service;

import com.stackroute.entity.User;
import com.stackroute.entity.UserRoles;
import com.stackroute.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationServiceTest {
    @InjectMocks
    AuthenticationServiceImpl authenticationService;
    @Mock
    UserRepository userRepository;

    @Test
    public void testWhenUserFound(){
        User user =  new User("tarhaneanuja@gmail.com","Anuja@17", UserRoles.GROUND_OWNER);
        Mockito.when(userRepository.findByUserEmailAndPassword(anyString(),anyString())).thenReturn(user);
        assertEquals(user,authenticationService.getUserByUserEmailAndPassword("tarhaneanuja@gmail.com", "Anuja@17"));
    }

    @Test
    public void testWhenUserEmailFound(){
        User user =  new User("tarhaneanuja@gmail.com","Anuja@17", UserRoles.GROUND_OWNER);
        Mockito.when(userRepository.findByUserEmail(anyString())).thenReturn(user);
        assertEquals(user,authenticationService.getUserByUserEmail("tarhaneanuja@gmail.com"));
    }



}
*/
