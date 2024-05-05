package com.bookmyshow.bookmyshow.exceptions;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String message){
        super(message);
    }
}
