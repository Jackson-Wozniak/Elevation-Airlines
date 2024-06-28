package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.model.FlightTimeTable;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FlightTimeTableDTO {
    private String date;
    private int flightCount;
    private List<FlightDTO> flights;

    public FlightTimeTableDTO(FlightTimeTable timeTable){
        this.date = DateTimeUtils.format(timeTable.getDate());
        this.flights = timeTable.getFlightsToday().stream().map(FlightDTO::new).toList();
        this.flightCount = flights.size();
    }
}
