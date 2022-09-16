package com.stackroute.service;

import com.stackroute.entity.Chat;
import com.stackroute.entity.Messages;
import com.stackroute.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    Messages messages = new Messages();

    @Override
    public Chat startChat(Chat chat) {
        try {
            if(chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(chat.getPlayerAEmailId(), chat.getPlayerBEmailId())!=null || chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(chat.getPlayerBEmailId(), chat.getPlayerAEmailId())!=null){

                return null;
            }
            for (Messages element : chat.getMessages()) {
                element.setMessageTime(LocalTime.now());
                element.setMessageDate(LocalDate.now());
            }
            return chatRepository.save(chat);

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Chat updateChat(Chat chat) {
        try{

            List<Messages> messagesList = getMessages(chat.getChatId());
            Chat updatedChat = new Chat();
            if(chatRepository.existsById(chat.getChatId())){

                updatedChat.setChatId(chat.getChatId());
                updatedChat.setPlayerAEmailId(chat.getPlayerAEmailId());
                updatedChat.setPlayerBEmailId(chat.getPlayerBEmailId());
                for (Messages element : chat.getMessages()) {
                    messagesList.add(element);
                    element.setMessageDate(LocalDate.now());
                    element.setMessageTime(LocalTime.now());

                }
                updatedChat.setMessages( messagesList);

                chatRepository.save(updatedChat);
                return updatedChat;
            }else {

                return null;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Chat getChats(String emailId1, String emailId2) {
        Chat chat = chatRepository.findByPlayerAEmailIdAndPlayerBEmailId(emailId1,emailId2);
        if(chat == null){
            return null;
        }
        return chat;
    }

    @Override
    public List<String> getPlayers(String emailId) {
        List<Chat> chats = chatRepository.findByPlayerAEmailId(emailId);
        List<String> players = new ArrayList<>();
        String player = null;
      for(Chat chat:chats){
        player =   chat.getPlayerBEmailId();
        players.add(player);
      }
      System.out.println(players.toString());
        System.out.println(chats);
        return players;
    }

    public List<Messages> getMessages(String chatId){
        Chat chat =  chatRepository.findById(chatId).get();
        return chat.getMessages();
    }




}
