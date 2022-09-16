package com.stackroute.service;

import com.stackroute.Entity.Teamup;
import com.stackroute.constantEnums.RequestStatus;
import com.stackroute.repository.TeamupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamupServiceImpl implements TeamupService{
    
    @Autowired(required = false)
    private TeamupRepository teamupRepository;

    @Override
    public Teamup sendInvites(Teamup teamup) throws Exception {
        teamup.setRequestId(UUID.randomUUID().toString());
        teamup.setStatusOfRequest(RequestStatus.PENDING);
        teamupRepository.save(teamup);
        return teamup;
    }

    @Override
    public String updateInvite(String requestId, RequestStatus requestStatus) throws Exception {
        Teamup teamup = teamupRepository.findByRequestId(requestId);
        teamup.setStatusOfRequest(requestStatus);
        teamupRepository.save(teamup);
        return String.valueOf(teamup.getStatusOfRequest());
    }

    @Override
    public List<Teamup> getRecievedInvitations(String requestedPlayerEmail) throws Exception {
        List<Teamup> inviteList = teamupRepository.findByRequestedPlayerEmail(requestedPlayerEmail);
        if(inviteList.isEmpty()) {
            throw new Exception();
        }
        return inviteList;
    }

    @Override
    public List<Teamup> getSentInvitations(String senderEmail) throws Exception {
        List<Teamup> inviteList = teamupRepository.findBySenderEmail(senderEmail);
        if(inviteList.isEmpty()) {
            throw new Exception();
        }
        return inviteList;
    }

    @Override
    public void deleteRequest(String requestId) {
        teamupRepository.deleteById(requestId);
    }
}
