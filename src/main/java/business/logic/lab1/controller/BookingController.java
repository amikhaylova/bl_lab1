package business.logic.lab1.controller;

import business.logic.lab1.exceptions.BookingFailException;
import business.logic.lab1.exceptions.IncorrectDateException;
import business.logic.lab1.dto.BookingDTO;
import business.logic.lab1.model.Hotel;
import business.logic.lab1.service.BookingAPIService;
import business.logic.lab1.service.HotelsAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Validated
public class BookingController {

    @Autowired
    private HotelsAPIService hotelsAPIService;

    @Autowired
    private BookingAPIService bookingAPIService;

    @GetMapping(path = "/hotels")
    public List<Hotel> getListOfHotels(@RequestParam("city") String city,
                                       @RequestParam("checkIn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkIn,
                                       @RequestParam("checkOut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOut,
                                       @RequestParam("guests") @Min(value = 1, message = "Number of guests must be greater than 0") int guests) {

        Date currentDate = new Date();
        if (checkIn.before(currentDate) || checkOut.before(currentDate))
            throw new IncorrectDateException("Date can't be before current date.");

        if (checkIn.after(checkOut))
            throw new IncorrectDateException("Check in date can't be after check out date.");

        return hotelsAPIService.geHotelSuggestions(city, checkIn, checkOut, guests);
    }

    @PostMapping(path = "/book")
    public ResponseEntity<?> bookHotel(@RequestBody BookingDTO bookingDTO) {
        if (!bookingAPIService.makeBookingRequest(bookingDTO))
            throw new BookingFailException("Booking failed.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
