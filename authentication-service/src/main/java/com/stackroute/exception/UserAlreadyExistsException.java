package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT,reason = "User already Exists")
public class UserAlreadyExistsException extends Throwable {


}
