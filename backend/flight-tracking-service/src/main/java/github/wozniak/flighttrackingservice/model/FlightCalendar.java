package github.wozniak.flighttrackingservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FlightCalendar {
    private List<LocalDate> dates;
    private List<FlightTimeTable> daysInCalendar;

    public FlightCalendar(List<FlightTimeTable> daysInCalendar){
        this.daysInCalendar = daysInCalendar;
        this.dates = daysInCalendar.stream().map(FlightTimeTable::getDate).toList();
    }
}
