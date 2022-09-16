package com.stackroute.player.custom.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistException extends  Exception{
    private  String message;

}
