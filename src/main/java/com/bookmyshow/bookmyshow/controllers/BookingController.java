package com.bookmyshow.bookmyshow.controllers;

import com.bookmyshow.bookmyshow.dtos.BookMovieRequestDto;
import com.bookmyshow.bookmyshow.dtos.BookMovieResponseDto;
import com.bookmyshow.bookmyshow.dtos.ResponseStatus;
import com.bookmyshow.bookmyshow.exceptions.ShowNotFoundException;
import com.bookmyshow.bookmyshow.exceptions.ShowSeatUnavailableException;
import com.bookmyshow.bookmyshow.exceptions.UserNotFoundException;
import com.bookmyshow.bookmyshow.models.Booking;
import com.bookmyshow.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/bookmovie")
    public BookMovieResponseDto bookMovie(@RequestBody BookMovieRequestDto bookMovieRequestDto) throws UserNotFoundException, ShowNotFoundException, ShowSeatUnavailableException {
        Booking booking = bookingService.bookMovie(
                    bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowSeatIds()
            );

        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
        bookMovieResponseDto.setBookingId(booking.getId());
        bookMovieResponseDto.setAmount(booking.getAmount());
        bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);

        return bookMovieResponseDto;
    }
}
