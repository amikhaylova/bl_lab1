package business.logic.lab1.service;

import business.logic.lab1.exceptions.BookingFailException;
import business.logic.lab1.dto.BookingDTO;
import business.logic.lab1.repository.BookingRepository;
import business.logic.lab1.utils.BookingEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Service
public class BookingAPIService {

    private final RestTemplate restTemplate;
    private String url = "http://127.0.0.1:8090/booking";
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingEntityManager bookingEntityManager;

    public BookingAPIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public boolean makeBookingRequest(BookingDTO dto) {
        HttpEntity<BookingDTO> request = new HttpEntity<>(dto);
        ResponseEntity<?> response = restTemplate
                .exchange(url, HttpMethod.POST, request, BookingDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            bookingRepository.save(bookingEntityManager.transferFromDtoToBooking(dto));
            return true;
        } else {
            throw new BookingFailException("Booking failed.");
        }
    }
}
