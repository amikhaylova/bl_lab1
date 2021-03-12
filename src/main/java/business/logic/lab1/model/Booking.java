package business.logic.lab1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
@Getter
@Setter
public class Booking {
    @Column(name = "bookingID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName="userID")
    private User user;

    @Column(name = "hotelID")
    private Long hotel;

    @Column(name = "numberOfGuests")
    private Integer guests;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "checkInDate")
    private Date checkIn;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "checkOutDate")
    private Date checkOut;
}
