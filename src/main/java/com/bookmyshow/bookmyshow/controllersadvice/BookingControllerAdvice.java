package com.bookmyshow.bookmyshow.controllersadvice;

import com.bookmyshow.bookmyshow.dtos.ResponseStatus;
import com.bookmyshow.bookmyshow.exceptions.ShowNotFoundException;
import com.bookmyshow.bookmyshow.exceptions.ShowSeatUnavailableException;
import com.bookmyshow.bookmyshow.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BookingControllerAdvice {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ShowNotFoundException.class)
    public ResponseEntity<String> showNotFoundException(ShowNotFoundException showNotFoundException){
        return new ResponseEntity<>(showNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ShowSeatUnavailableException.class)
    public ResponseEntity<String> showSeatUnavailableException(ShowSeatUnavailableException showSeatUnavailableException){
        return new ResponseEntity<>(showSeatUnavailableException.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
