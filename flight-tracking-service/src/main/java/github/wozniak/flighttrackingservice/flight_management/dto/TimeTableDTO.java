package github.wozniak.flighttrackingservice.flight_management.dto;

import github.wozniak.flighttrackingservice.flight_management.model.TimeTable;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TimeTableDTO {
    private String date;
    private int flightCount;
    private List<FlightSummaryDTO> flights;

    public TimeTableDTO(TimeTable timeTable){
        this.date = DateTimeUtils.format(timeTable.getDate());
        this.flightCount = timeTable.getFlightsToday().size();
        this.flights = timeTable.getFlightsToday().stream().map(FlightSummaryDTO::new).toList();
    }
}
