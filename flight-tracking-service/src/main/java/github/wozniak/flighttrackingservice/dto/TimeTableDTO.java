package github.wozniak.flighttrackingservice.dto;

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
}
