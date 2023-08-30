package github.wozniak.flightbookingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Jackson Wozniak
 * @created : 8/19/2023, Saturday
 **/
@Entity(name = "ticket")
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "cost")
    private Double ticketCost;

    @ManyToOne
    @JoinColumn(name="flight_identifier", nullable=false)
    private Flight flight;
}
