package github.wozniak.flighttrackingservice.model;

import github.wozniak.flighttrackingservice.entity.Flight;
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
public class FlightTimeTable {

    private LocalDate date;
    private List<Flight> flightsToday;

    public static List<FlightTimeTable> generate(Map<LocalDate, ArrayList<Flight>> flights){
        List<FlightTimeTable> daysInCalendar = new ArrayList<>();
        for(Map.Entry<LocalDate, ArrayList<Flight>> date : flights.entrySet()){
            daysInCalendar.add(new FlightTimeTable(date.getKey(), date.getValue()));
        }
        return daysInCalendar;
    }
}
