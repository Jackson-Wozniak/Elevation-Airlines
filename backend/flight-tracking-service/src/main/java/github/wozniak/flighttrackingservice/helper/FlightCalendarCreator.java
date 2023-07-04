package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.entity.ScheduledRoute;
import github.wozniak.flighttrackingservice.model.FlightTimeTable;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlightCalendarCreator {

    private final RouteGenerator routeGenerator;
    private final PlaneService planeService;
    private final FlightService flightService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    public List<LocalDate> missingDays(LocalDate startDate, LocalDate endDate){
        Set<LocalDate> dates = flightService.findAllFlights().stream()
                .map(flight -> flight.getTakeOffDateTime().toLocalDate())
                .collect(Collectors.toSet());
        List<LocalDate> missingDays = new ArrayList<>();
        for(LocalDate date : DateTimeUtils.allDatesInRange(startDate, endDate)){
            if(!dates.contains(date)) missingDays.add(date);
        }
        return missingDays;
    }

    public FlightTimeTable createDaysTimeTable(LocalDate date, List<ScheduledRoute> dailyRoutes){
        ArrayList<Flight> flightsToday = new ArrayList<>(dailyRoutes.stream()
                .map(route -> new Flight(route, LocalDateTime.of(date, route.getTime())))
                .toList());

        //TODO: add round trip for daily flights (fly back to departure airport after landing in destination)

        //for each plane not being used by a daily flight, schedule 1 random flight
        planeService.findUnusedPlanes(flightsToday.stream()
                .map(Flight::getPlane).toList())
                .forEach(plane -> flightsToday.add(new Flight(
                        plane,
                        routeGenerator.flightFromUnitedStates(plane, 24),
                        LocalDateTime.of(date, DateTimeUtils.createTimeOfFlight())))
                );
        return new FlightTimeTable(date, flightsToday);
    }
}
