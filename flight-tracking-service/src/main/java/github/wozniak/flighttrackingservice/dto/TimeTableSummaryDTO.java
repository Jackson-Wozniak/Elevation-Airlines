package github.wozniak.flighttrackingservice.dto;

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
}