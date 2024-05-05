package com.bookmyshow.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private float amount;
    private Long bookingId;

    private ResponseStatus responseStatus;
}
