package com.bookmyshow.bookmyshow.services;

import com.bookmyshow.bookmyshow.models.Show;
import com.bookmyshow.bookmyshow.models.ShowSeat;
import com.bookmyshow.bookmyshow.models.ShowSeatType;
import com.bookmyshow.bookmyshow.repositories.ShowSeatTypeRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    @Autowired
    ShowSeatTypeRepository showSeatTypeRepository;
    public float getTotalAmount(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        float amount = 0;
        for(ShowSeat showSeat : showSeats){
            for (ShowSeatType showSeatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount+=showSeatType.getPrice();
                }
            }
        }

        return amount;
    }
}
