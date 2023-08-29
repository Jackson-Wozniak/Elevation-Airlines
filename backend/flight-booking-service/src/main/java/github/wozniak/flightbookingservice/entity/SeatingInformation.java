package github.wozniak.flightbookingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 8/29/2023, Tuesday
 **/
/*
This class is used to track remaining seats, ticket prices, etc for flights. The TicketServicing.java
    helper class maintains remaining seat count based on demand, ticket pricing, and time till takeoff
 */
@Entity(name = "seatingInformation")
@Table(name = "seating_information")
@Getter
@Setter
@NoArgsConstructor
public class SeatingInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_identifier")
    private Flight flight;
}
