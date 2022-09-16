package com.stackroute.controller;

import com.stackroute.ID.SequenceGenerator;
import com.stackroute.controller.ChatController;
import com.stackroute.entity.Chat;
import com.stackroute.entity.Messages;
import com.stackroute.service.ChatServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.stackroute.entity.Chat.SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ChatControllerTest {
    @InjectMocks
    ChatController chatController;
    @Mock
    ChatServiceImpl chatService;
    @Mock
    SequenceGenerator sequenceGenerator;
    ResponseEntity responseEntity;
    Messages messages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
    List<Messages> list = new ArrayList<>();

    Chat chat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",list);
    @Test
    public void testWhenChatisCreated(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
       responseEntity = new ResponseEntity(chat,HttpStatus.CREATED);
        List<Messages> checkList = new ArrayList<>();
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
       checkChat.setChatId("chatId-1"+Mockito.when(sequenceGenerator.sequenceNumber(SEQUENCE)).thenReturn(1));
       Mockito.when(chatService.startChat(checkChat)).thenReturn(chat);
       assertEquals(responseEntity,chatController.newChat(checkChat));

    }
    @Test
    public void testWhenChatisNotCreated(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        List<Messages> checkList = new ArrayList<>();

        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        checkChat.setChatId("chatId-1"+Mockito.when(sequenceGenerator.sequenceNumber(SEQUENCE)).thenReturn(1));
        Mockito.when(chatService.startChat(checkChat)).thenReturn(null);
        assertEquals(responseEntity,chatController.newChat(checkChat));
    }
    @Test
    public void testWhenChatisUpdated(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());

        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        responseEntity = new ResponseEntity(checkChat,HttpStatus.CREATED);
        Mockito.when(chatService.updateChat(chat)).thenReturn(checkChat);
        assertEquals(responseEntity.toString(),chatController.updateChat(chat).toString());

    }
    @Test
    public void testWhenChatisNotUpdated(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());

        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        Mockito.when(chatService.updateChat(chat)).thenReturn(null);
        assertEquals(responseEntity,chatController.updateChat(chat));
    }
    @Test
    public void testWhenGetChatisSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        responseEntity = new ResponseEntity(checkChat,HttpStatus.FOUND);
        Mockito.when(chatService.getChats(chat.getPlayerAEmailId(), chat.getPlayerBEmailId())).thenReturn(checkChat);
        assertEquals(responseEntity,chatController.getChat(chat.getPlayerAEmailId(), chat.getPlayerBEmailId()));
    }
@Test
    public void testWhenGetChatisNotSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        Mockito.when(chatService.getChats(checkChat.getPlayerAEmailId(), chat.getPlayerBEmailId())).thenReturn(null);
        assertEquals(responseEntity,chatController.getChat(chat.getPlayerAEmailId(), checkChat.getPlayerBEmailId()));
    }
    @Test
    public void testWhenGetPlayerisSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
       List<String> list = new ArrayList<>();
       list.add("anuja@gmail.com");
        Mockito.when(chatService.getPlayers(checkChat.getPlayerAEmailId())).thenReturn(list);
        assertEquals(list,chatController.getPlayers(checkChat.getPlayerAEmailId()));
    }
@Test
    public void testWhenGetPlayerisNotSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        List<String> list = new ArrayList<>();
        list.add("anuja@gmail.com");
        Mockito.when(chatService.getPlayers(checkChat.getPlayerAEmailId())).thenReturn(null);
        assertEquals(null,chatController.getPlayers(checkChat.getPlayerAEmailId()));
    }
}
