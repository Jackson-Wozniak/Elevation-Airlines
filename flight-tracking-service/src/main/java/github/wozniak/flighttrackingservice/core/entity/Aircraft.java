package github.wozniak.flighttrackingservice.core.entity;

import github.wozniak.flighttrackingservice.core.enums.AircraftCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "aircraft")
@Table(name = "aircrafts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aircraft {
    @Id
    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "maker")
    private String make;

    @Column(name = "model")
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "aircraft_category")
    private AircraftCategory aircraftCategory;

    @Column(name = "seating_capacity")
    private Integer seatingCapacity;

    @Column(name = "cruising_speed_knots")
    private Integer cruisingSpeedKnots;

    @Column(name = "range_miles")
    private Integer rangeMiles;

    public Aircraft(Builder builder){
        this.name = builder.make + " " + builder.model;
        this.make = builder.make;
        this.model = builder.model;
        this.aircraftCategory = builder.type;
        this.seatingCapacity = builder.seats;
        this.cruisingSpeedKnots = builder.cruiseSpeed;
        this.rangeMiles = builder.range;
    }

    public static class Builder{
        private final String make;
        private final String model;
        private final AircraftCategory type;
        private int seats;
        private int cruiseSpeed;
        private int range;

        public Builder(String make, String model, AircraftCategory type){
            this.make = make;
            this.model = model;
            this.type = type;
            this.seats = 0;
            this.cruiseSpeed = 0;
            this.range = 0;
        }

        public Builder seating(int seatCount){
            this.seats = seatCount;
            return this;
        }

        public Builder stats(int cruise, int range){
            this.cruiseSpeed = cruise;
            this.range = range;
            return this;
        }

        public Aircraft build(){
            return new Aircraft(this);
        }
    }
}
