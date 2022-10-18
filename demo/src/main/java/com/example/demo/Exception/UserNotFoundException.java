package com.example.demo.Exception;

import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id){
        super(MessageFormat.format("Could not found id: {}",id));
    }

}
