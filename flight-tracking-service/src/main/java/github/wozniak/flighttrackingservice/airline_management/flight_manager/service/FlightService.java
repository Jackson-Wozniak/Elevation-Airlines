package github.wozniak.flighttrackingservice.airline_management.flight_manager.service;

import github.wozniak.flighttrackingservice.core.exception.*;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Flight;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.exceptions.PlaneNotFoundException;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.model.TimeTable;
import github.wozniak.flighttrackingservice.airline_management.flight_manager.repository.FlightRepository;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.PlaneService;
import github.wozniak.flighttrackingservice.core.utils.DateTimeUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;

    public void saveFlights(List<Flight> flights){
        flightRepository.saveAll(flights);
    }

    public Flight findFlightsByIdentifier(long identifier){
        return flightRepository.findById(identifier)
                .orElseThrow(() -> new FlightNotFoundException("Cannot find flight #" + identifier));
    }

    public List<Flight> findFlightByCallSign(String callSign, PlaneService service){
        List<Flight> flights = flightRepository.findFlightsByCallSign(callSign);
        if(!flights.isEmpty()) return flights;
        if(!service.planeExists(callSign)){
            throw new PlaneNotFoundException(callSign + " does not exist");
        }
        throw new FlightQueryException("No flights are scheduled for " + callSign);
    }

    public Flight findLastFlightByCallSign(String callSign){
        return flightRepository.findFlightsByCallSign(callSign).stream()
                .max(Comparator.comparing(Flight::getTakeOffDateTime))
                .orElseThrow(() -> new FlightNotFoundException("Cannot find most recent flight"));
    }

    public List<Flight> findFlightsByAirport(String icao, boolean isDeparture){
        if(!airportService.airportExists(icao)) throw new AirportNotFoundException(icao + " does not exist");
        List<Flight> flights = isDeparture ? flightRepository.findFlightsByDeparture(icao)
                : flightRepository.findFlightsByDestination(icao);
        if(flights.isEmpty()) throw new FlightQueryException("No flights are scheduled " + (isDeparture ? "from " : "to ") + icao);

        return flights;
    }

    public List<Flight> findFlights(String departure, String dest){
        if(!airportService.airportExists(departure)) throw new AirportNotFoundException(departure + " does not exist");
        if(!airportService.airportExists(dest)) throw new AirportNotFoundException(dest + " does not exist");

        return flightRepository.findAll().stream()
                .filter(flight -> flight.getRoute().getDepartureAirport().getIcaoCode().equals(departure))
                .filter(flight -> flight.getRoute().getDestinationAirport().getIcaoCode().equals(dest))
                .toList();
    }

    public List<Flight> findFlightsByDate(String date){
        if(!DateTimeUtils.isValid(date)) throw new DateFormatException(date, false);
        List<Flight> flights = flightRepository.findFlightsByDate(DateTimeUtils.stringToSQLDate(date));
        if(flights.isEmpty()) throw new FlightQueryException("No flights are scheduled on " + date);
        return flights;
    }

    public List<TimeTable> findFlightsByDateRange(String start, String end){
        if(start == null || start.isBlank()) throw new FlightQueryException("Start date is invalid");
        if(end == null || end.isBlank()) throw new FlightQueryException("End date is invalid");

        HashMap<LocalDate, ArrayList<Flight>> flightsOnDate = new HashMap<>();
        List<LocalDate> dates = DateTimeUtils.allDatesInRange(
                DateTimeUtils.toDate(start), DateTimeUtils.toDate(end), true);

        for (LocalDate date : dates) {
            flightsOnDate.put(date, new ArrayList<>());
        }

        flightRepository.findAll().forEach(flight -> {
            if(!dates.contains(flight.getTakeOffDateTime().toLocalDate())) return;
            flightsOnDate.get(flight.getTakeOffDateTime().toLocalDate()).add(flight);
        });
        return TimeTable.generate(flightsOnDate);
    }

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public List<Flight> findAllFlights(String departureCode, String destinationCode){
        boolean departureEmpty = departureCode == null || departureCode.isBlank();
        boolean destinationEmpty = destinationCode == null || destinationCode.isBlank();

        if(departureEmpty && destinationEmpty) return findAllFlights();
        if(departureEmpty) return findFlightsByAirport(destinationCode, false);
        if(destinationEmpty) return findFlightsByAirport(departureCode, true);
        return findAllFlights(departureCode, destinationCode);
    }

    public List<Flight> findLiveFlights(){
        return flightRepository.findAll().stream()
                .filter(flight -> flight.getTakeOffDateTime().toLocalDate().equals(LocalDate.now()))
                .filter(flight -> DateTimeUtils.isLiveFlight(
                        flight.getTakeOffDateTime(), flight.getRoute().getFlightDurationHours()))
                .toList();
    }

    @Modifying
    @Transactional
    public void deleteAllFlights(){
        flightRepository.deleteAll();
    }

    @Modifying
    @Transactional
    public void deletePastFlights(){
        List<Flight> pastFlights = flightRepository.findAll().stream()
                .filter(flight -> flight.getLandingDateTime().toLocalDate().isBefore(LocalDate.now()))
                .toList();
        flightRepository.deleteAll(pastFlights);
    }
}
