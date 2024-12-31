package github.wozniak.flighttrackingservice.dto;

import github.wozniak.flighttrackingservice.model.TimeTable;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TimeTableSummaryDTO {
    private String date;
    private int flightCount;
    private List<FlightSummaryDTO> flights;

    public TimeTableSummaryDTO(TimeTable timeTable){
        this.date = DateTimeUtils.format(timeTable.getDate());
        this.flightCount = timeTable.getFlightsToday().size();
        this.flights = timeTable.getFlightsToday().stream().map(FlightSummaryDTO::new).toList();
    }
}