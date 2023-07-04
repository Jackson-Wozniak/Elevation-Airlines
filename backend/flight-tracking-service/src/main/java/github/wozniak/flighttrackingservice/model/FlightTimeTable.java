package github.wozniak.flighttrackingservice.model;

import github.wozniak.flighttrackingservice.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FlightTimeTable {

    private LocalDate date;
    private List<Flight> flightsToday;
}
