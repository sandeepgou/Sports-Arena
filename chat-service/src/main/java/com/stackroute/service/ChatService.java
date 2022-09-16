package com.stackroute.service;

import com.stackroute.entity.Chat;

import java.util.List;

public interface ChatService {
    Chat startChat(Chat chat);

    Chat updateChat(Chat chat);

    Chat getChats(String emailId1, String emailId2);

    List<String> getPlayers(String emailId);
}
