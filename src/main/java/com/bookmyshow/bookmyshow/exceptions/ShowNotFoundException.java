package com.bookmyshow.bookmyshow.exceptions;

import com.bookmyshow.bookmyshow.repositories.ShowRepository;

public class ShowNotFoundException extends Throwable {
    public ShowNotFoundException(String message){
        super(message);
    }
}
