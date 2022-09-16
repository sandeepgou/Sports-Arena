package com.stackroute.service;

import com.stackroute.entity.User;
import com.stackroute.entity.UserDTO;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
public UserRepository userRepository;
    UserDTO userDTO = new UserDTO();
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserEmail()).isPresent())
        {
          throw  new UserAlreadyExistsException();
        }

        else{
            return userRepository.save(user);
      }

    }

    @Override
    public User getUserByUserEmailAndPassword(String userEmail, String password) {
    return userRepository.findByUserEmailAndPassword(userEmail,password);
    }
    @Override
    public User getUserByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }
}
