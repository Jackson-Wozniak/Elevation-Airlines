package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.entity.ScheduledRoute;
import github.wozniak.flighttrackingservice.exception.FlightQueryException;
import github.wozniak.flighttrackingservice.exception.RouteGeneratorException;
import github.wozniak.flighttrackingservice.model.FlightTimeTable;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import github.wozniak.flighttrackingservice.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FlightCalendarCreator {

    private final RouteGenerator routeGenerator;
    private final PlaneService planeService;
    private final FlightService flightService;
    private final ScheduledRouteService scheduledRouteService;

    public boolean isDayMissing(LocalDate date){
        Set<LocalDate> dates = flightService.findAllFlights().stream()
                .map(flight -> flight.getTakeOffDateTime().toLocalDate())
                .collect(Collectors.toSet());
        return !dates.contains(date);
    }

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
        scheduledRouteService.findAvailablePlanes().forEach(plane -> {
            double maxHours;
            try{
                Flight latestFlight = flightService.findLastFlightByCallSign(plane.getCallSign());
                maxHours = Duration.between(latestFlight.getTakeOffDateTime(), date.atTime(23, 59)).toSeconds() * 3600;
                flightsToday.add(new Flight(
                        plane,
                        routeGenerator.fromAirport(latestFlight.getRoute().getDestinationAirport(),
                                plane, maxHours),
                        LocalDateTime.of(date, DateTimeUtils.createTimeOfFlight(latestFlight.getLandingHour())))
                );
            }catch (FlightQueryException ex){
                flightsToday.add(new Flight(
                        plane, routeGenerator.fromUnitedStates(plane, 11), LocalDateTime.of(date, DateTimeUtils.createTimeOfFlight(12))));
            }catch (Exception ex){
                //do something here later to solve for dismissed flights
                //Currently the scheduling approach (# of flight hours and earliest flight times
                //should reduce the possibility of this, but it should be more full-proof
                System.out.println(ex.getMessage());
            }
        });
        return new FlightTimeTable(date, flightsToday);
    }
}
