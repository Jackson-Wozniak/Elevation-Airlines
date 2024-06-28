package github.wozniak.flighttrackingservice.helper;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.entity.Plane;
import github.wozniak.flighttrackingservice.entity.Route;
import github.wozniak.flighttrackingservice.entity.ScheduledRoute;
import github.wozniak.flighttrackingservice.exception.FlightNotFoundException;
import github.wozniak.flighttrackingservice.exception.FlightQueryException;
import github.wozniak.flighttrackingservice.model.TimeTable;
import github.wozniak.flighttrackingservice.service.FlightService;
import github.wozniak.flighttrackingservice.service.PlaneService;
import github.wozniak.flighttrackingservice.service.ScheduledRouteService;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        for(LocalDate date : DateTimeUtils.allDatesInRange(startDate, endDate, false)){
            if(!dates.contains(date)) missingDays.add(date);
        }
        return missingDays;
    }

    public TimeTable createDaysTimeTable(LocalDate date, List<ScheduledRoute> dailyRoutes){
        ArrayList<Flight> flightsToday = new ArrayList<>(dailyRoutes.stream()
                .map(route -> new Flight(route, LocalDateTime.of(date, route.getTime())))
                .toList());

        //add round trip for daily flights (fly back to departure airport after landing in destination)
        List<Flight> copy = new ArrayList<>(flightsToday);
        copy.forEach(flight -> flightsToday.add(new Flight(
                flight.getPlane(),
                new Route(flight.getRoute().getDestinationAirport(),
                        flight.getRoute().getDepartureAirport(),
                        flight.getPlane()),
                flight.getLandingDateTime().plusMinutes(30)
        )));

        //for each plane not being used by a daily flight, schedule 1 random flight
        scheduledRouteService.findAvailablePlanes().forEach(plane -> {
            try{
                Flight latestFlight = flightService.findLastFlightByCallSign(plane.getCallSign());
                flightsToday.addAll(scheduleFlightsUntilEOD(plane, date, latestFlight));
            }catch (FlightNotFoundException ex){
                flightsToday.add(new Flight(
                        plane, routeGenerator.fromUnitedStates(plane, 11), LocalDateTime.of(date, DateTimeUtils.createTimeOfFlight(12))));
            }
        });
        return new TimeTable(date, flightsToday);
    }

    /*
    Starting at the last flights time of landing (+30min), schedule flights throughout the day with
    30min intervals between until the landing time crosses over into the next day. Departures are set by
    the planes current location
     */
    public List<Flight> scheduleFlightsUntilEOD(Plane plane, LocalDate date, Flight latestFlight){
        List<Flight> newFlights = new ArrayList<>();
        if(latestFlight.getLandingDateTime().toLocalDate().isAfter(date)) return List.of();
        while(true){
            Route route = routeGenerator.fromAirport(latestFlight.getRoute().getDestinationAirport(), plane, 11);
            LocalDateTime takeOffTime = LocalDateTime.of(date, latestFlight.getLandingDateTime().toLocalTime().plusMinutes(30));

            Flight newestFlight = new Flight(plane, route, takeOffTime);
            newFlights.add(newestFlight);
            //need to make sure this pus 30 is doing what I want it to (haven't tested yet)
            //intended so that the next take-off times in loop aren't a different day
            if(newestFlight.getLandingDateTime().plusMinutes(30).toLocalDate().isAfter(date)){
                break;
            }
            latestFlight = newestFlight;
        }
        return newFlights;
    }
}
