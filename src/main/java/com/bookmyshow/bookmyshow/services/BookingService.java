package com.bookmyshow.bookmyshow.services;

import com.bookmyshow.bookmyshow.exceptions.ShowNotFoundException;
import com.bookmyshow.bookmyshow.exceptions.ShowSeatUnavailableException;
import com.bookmyshow.bookmyshow.exceptions.UserNotFoundException;
import com.bookmyshow.bookmyshow.models.*;
import com.bookmyshow.bookmyshow.repositories.BookingRepository;
import com.bookmyshow.bookmyshow.repositories.ShowRepository;
import com.bookmyshow.bookmyshow.repositories.ShowSeatRepository;
import com.bookmyshow.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId,Long showId, List<Long> showSeatId) throws UserNotFoundException, ShowNotFoundException, ShowSeatUnavailableException {

//        Fetching user by userId
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not available");
        }

        User user = optionalUser.get();

//        Fetching Show by showId
        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show not available");
        }

        Show show = optionalShow.get();

//        Fetching showSeats
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatId);

        for (ShowSeat showSeat : showSeats){
            if(!((showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED)
                            && Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15))){
                throw new ShowSeatUnavailableException("Show seat(s) unavailable");
            }
        }
//        Blocking show seats
        Date blockedAt = new Date();
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setBlockedAt(blockedAt);
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }


//        Completing booking
        Booking booking = new Booking();

        booking.setUser(user);
        booking.setShow(show);
        booking.setBookedAt(blockedAt);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setAmount(priceCalculatorService.getTotalAmount(savedShowSeats,show));

        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}
