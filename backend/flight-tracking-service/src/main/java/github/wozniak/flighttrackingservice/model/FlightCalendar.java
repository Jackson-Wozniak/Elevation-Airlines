package github.wozniak.flighttrackingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FlightCalendar {
    private List<LocalDate> dates;
    private List<DailyFlightTimeTable> daysInCalendar;

    public FlightCalendar(List<DailyFlightTimeTable> daysInCalendar){
        this.daysInCalendar = daysInCalendar;
        this.dates = daysInCalendar.stream().map(DailyFlightTimeTable::getDate).toList();
    }
}
