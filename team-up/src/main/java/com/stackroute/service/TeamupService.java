package com.stackroute.service;

import com.stackroute.Entity.Teamup;
import com.stackroute.constantEnums.RequestStatus;

import java.util.List;

public interface TeamupService {

    Teamup sendInvites(Teamup teamup) throws Exception;

    String updateInvite(String requestId, RequestStatus requestStatus) throws Exception;
    List<Teamup> getRecievedInvitations(String requestedPlayerEmail) throws Exception;

    List<Teamup> getSentInvitations(String senderEmail) throws Exception;

    void deleteRequest(String requestId);
}
