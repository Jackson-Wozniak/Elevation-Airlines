package github.wozniak.flighttrackingservice.entity;

import github.wozniak.flighttrackingservice.dto.*;
import github.wozniak.flighttrackingservice.properties.PlaneList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "plane")
@Table(name = "planes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @Column(name = "cruising_speed_knots")
    private Integer cruisingSpeedKnots;

    @Column(name = "range_miles")
    private Integer rangeMiles;

    public Plane(String callSign, PlaneList.PlaneModel planeModel){
        this.callSign = callSign;
        this.model = planeModel.getModel();
        this.seatingCapacity = planeModel.getSeatingCapacity();
        this.luxurySeats = planeModel.getLuxurySeats();
        this.cruisingSpeedKnots = planeModel.getCruisingSpeedKnots();
        this.rangeMiles = planeModel.getRangeMiles();
    }

    public PlaneDTO getDTO(){
        return new PlaneDTO(callSign, model, seatingCapacity, luxurySeats, cruisingSpeedKnots, rangeMiles);
    }

    public PlaneSummaryDTO getDTOSummary(){
        return new PlaneSummaryDTO(callSign, model);
    }
}
