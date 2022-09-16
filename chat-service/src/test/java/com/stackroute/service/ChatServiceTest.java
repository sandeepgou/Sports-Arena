package com.stackroute.service;

import com.stackroute.entity.Chat;
import com.stackroute.entity.Messages;
import com.stackroute.repository.ChatRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ChatServiceTest {
    @InjectMocks
    ChatServiceImpl chatService;
    @Mock
    ChatRepository chatRepository;

    Messages messages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent", LocalTime.now(), LocalDate.now());
    List<Messages> list = new ArrayList<>();
    ResponseEntity responseEntity;
    Chat chat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",list);
    @Test
    public void testWhenstartChatisSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());

        List<Messages> checkList = new ArrayList<>();
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        Mockito.when(chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(checkChat.getPlayerAEmailId(),checkChat.getPlayerBEmailId())).thenReturn(null);
        Mockito.when(chatRepository.save(checkChat)).thenReturn(chat);
        assertEquals(chat,chatService.startChat(checkChat));

    }

    @Test
    public void testWhenstartChatisNotSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        Mockito.when(chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(checkChat.getPlayerAEmailId(),checkChat.getPlayerBEmailId())).thenReturn(chat);
        Mockito.when(chatRepository.save(checkChat)).thenReturn(chat);
        assertEquals(null,chatService.startChat(checkChat));
    }
    @Test
    public void testWhenUpdateChatisSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        Mockito.when(chatRepository.findById(checkChat.getChatId())).thenReturn(Optional.ofNullable(checkChat));
        Mockito.when(chatRepository.existsById(checkChat.getChatId())).thenReturn(true);
        Mockito.when(chatRepository.save(chat)).thenReturn(checkChat);
        assertEquals(checkChat.toString(),chatService.updateChat(chat).toString());
    }
    @Test
    public void testWhenUpdateChatisNotSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        Mockito.when(chatRepository.findById(checkChat.getChatId())).thenReturn(Optional.ofNullable(checkChat));
        Mockito.when(chatRepository.existsById(checkChat.getChatId())).thenReturn(false);
        Mockito.when(chatRepository.save(chat)).thenReturn(null);
        assertEquals(null,chatService.updateChat(chat));
    }
    @Test
    public void testWhenGetChatsisSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        Mockito.when(chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(checkChat.getPlayerAEmailId(),checkChat.getPlayerBEmailId())).thenReturn(chat);
        assertEquals(chat,chatService.getChats(checkChat.getPlayerAEmailId(),checkChat.getPlayerBEmailId()));
    }
    @Test
    public void testWhenGetChatisNotSuccess(){
        Messages checkMessages = new Messages("playerA@gmail.com","playerB@gmail.com","messageContent",LocalTime.now(),LocalDate.now());
        List<Messages> checkList = new ArrayList<>();
        checkList.add(checkMessages);
        Chat checkChat = new Chat("chatId-1","playerA@gmail.com","playerB@gmail.com",checkList);
        Mockito.when(chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(checkChat.getPlayerAEmailId(),checkChat.getPlayerBEmailId())).thenReturn(null);
        assertEquals(null,chatService.getChats(checkChat.getPlayerAEmailId(),checkChat.getPlayerBEmailId()));
    }

}
