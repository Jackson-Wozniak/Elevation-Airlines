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
public class TimeTableDTO {
    private String date;
    private int flightCount;
    private List<FlightDTO> flights;

    public TimeTableDTO(TimeTable timeTable){
        this.date = DateTimeUtils.format(timeTable.getDate());
        this.flightCount = timeTable.getFlightsToday().size();
        this.flights = timeTable.getFlightsToday().stream().map(FlightDTO::new).toList();
    }
}
