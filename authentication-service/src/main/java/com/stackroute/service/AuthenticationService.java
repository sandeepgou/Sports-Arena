package com.stackroute.service;

import com.stackroute.entity.User;
import com.stackroute.exception.UserAlreadyExistsException;

public interface AuthenticationService {
User saveUser(User user) throws UserAlreadyExistsException;


     User getUserByUserEmailAndPassword(String userEmail, String password);
      User getUserByUserEmail(String userEmail);
}
