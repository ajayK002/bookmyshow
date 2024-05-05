package com.bookmyshow.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Booking extends BaseModel {

    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private Show show;

    private float amount;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToMany
    private List<Payment> payments;

    private Date bookedAt;
}
