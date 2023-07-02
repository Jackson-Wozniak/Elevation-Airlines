package github.wozniak.flighttrackingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "plane")
@Table(name = "planes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plane {

    @Id
    @Column(name = "call_sign")
    private String callSign;

    @Column(name = "model")
    private String model;

    @Column(name = "seat_capacity")
    private Integer seatingCapacity;

    @Column(name = "luxury_seats")
    private Integer luxurySeats;

    @Column(name = "base_seating_price")
    private Double baseSeatingPrice;
}
