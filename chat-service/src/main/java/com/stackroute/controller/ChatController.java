package com.stackroute.controller;

import com.stackroute.ID.SequenceGenerator;
import com.stackroute.entity.Chat;
import com.stackroute.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stackroute.entity.Chat.SEQUENCE;

@RestController
@RequestMapping("/api/v1")
public class ChatController {
    @Autowired
    ChatServiceImpl chatService;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    @PostMapping("/newmessage")
    public ResponseEntity<Chat> newChat(@RequestBody Chat chat){
        try{
            chat.setChatId("ChatId-"+sequenceGenerator.sequenceNumber(SEQUENCE));
            Chat newChat = chatService.startChat(chat);
            if(newChat==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newChat,HttpStatus.CREATED);

        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/message")
    public ResponseEntity<Chat> updateChat(@RequestBody Chat chat){
        try{
            System.out.println("inside controller");
            Chat updatedChat = chatService.updateChat(chat);
            if(updatedChat==null){
                System.out.println("inside controller if");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(updatedChat,HttpStatus.CREATED);
        }catch (Exception ex){
            System.out.println("inside catch");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chat/{emailId1}/{emailId2}")
    public ResponseEntity<Chat> getChat(@PathVariable String emailId1,@PathVariable String emailId2){
        try{
            Chat getChat = chatService.getChats(emailId1,emailId2);
            if(getChat == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(getChat,HttpStatus.FOUND);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/chat/{emailId}")
    public List getPlayers(@PathVariable String emailId){
        try{
            List<String> players = chatService.getPlayers(emailId);
            if(players == null){
                return null;
            }
            return players;
        }catch(Exception ex){
            return null;
        }
    }

}
