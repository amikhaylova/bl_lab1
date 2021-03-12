package business.logic.lab1.utils;

import business.logic.lab1.model.Booking;
import business.logic.lab1.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingEntityManager {

    @Autowired
    private UserEntityManager userEntityManager;

    public Booking transferFromDtoToBooking(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setUser(userEntityManager.getUserByEmail(dto.getEmail()));
        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());
        booking.setGuests(dto.getGuests());
        booking.setHotel(dto.getHotelID());
        return booking;
    }

}
