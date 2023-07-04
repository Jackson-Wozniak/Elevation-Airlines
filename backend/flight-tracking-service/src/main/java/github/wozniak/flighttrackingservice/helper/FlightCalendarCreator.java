package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.model.DailyFlightTimeTable;
import github.wozniak.flighttrackingservice.service.PlaneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class FlightCalendarCreator {

    private final RouteGenerator routeGenerator;
    private final PlaneService planeService;

    public void validateOneWeekOfFlights(){

    }

    public DailyFlightTimeTable createDaysTimeTable(LocalDate date){
        return null;
    }
}
