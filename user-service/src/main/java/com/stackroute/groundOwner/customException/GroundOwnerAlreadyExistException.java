package com.stackroute.groundOwner.customException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class GroundOwnerAlreadyExistException extends  Exception{
    private  String message;

}
