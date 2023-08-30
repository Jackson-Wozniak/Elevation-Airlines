package github.wozniak.flightbookingservice.entity;

import github.wozniak.flightbookingservice.payload.FlightResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Jackson Wozniak
 * @created : 8/19/2023, Saturday
 **/
/*
This class is used to store details related to flights that are booked.
Details include flight identifier, plane type & seat info, ticket prices etc.
This class does not store any direct flight info (departure, destination) except take off time
& flight duration, and instead relies on the flight tracking service using the flight identifier
 */
@Entity(name = "flight")
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    private Long flightIdentifier;

    @Column(name = "takeOffTime")
    private LocalDateTime localDateTime;

    @Column(name = "duration")
    private Integer flightDurationMinutes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private SeatingInformation seatingInformation;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

    public Flight(FlightResponse response){

    }
}
