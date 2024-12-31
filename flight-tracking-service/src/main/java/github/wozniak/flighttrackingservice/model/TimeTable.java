package github.wozniak.flighttrackingservice.model;

import github.wozniak.flighttrackingservice.dto.TimeTableDTO;
import github.wozniak.flighttrackingservice.dto.TimeTableSummaryDTO;
import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.utils.DTOListMapper;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class TimeTable {

    private LocalDate date;
    private List<Flight> flightsToday;

    public static List<TimeTable> generate(Map<LocalDate, ArrayList<Flight>> flights){
        List<TimeTable> daysInCalendar = new ArrayList<>();
        for(Map.Entry<LocalDate, ArrayList<Flight>> date : flights.entrySet()){
            daysInCalendar.add(new TimeTable(date.getKey(), date.getValue()));
        }
        return daysInCalendar;
    }
}
