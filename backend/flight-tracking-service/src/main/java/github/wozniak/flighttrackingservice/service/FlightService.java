package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.exception.*;
import github.wozniak.flighttrackingservice.model.FlightTimeTable;
import github.wozniak.flighttrackingservice.repository.FlightRepository;
import github.wozniak.flighttrackingservice.utils.DateTimeUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public void saveFlights(List<Flight> flights){
        flightRepository.saveAll(flights);
    }

    public Flight findFlightsByIdentifier(long identifier){
        try{
            return flightRepository.findById(identifier)
                    .orElseThrow(() -> new FlightQueryException("Cannot find flight #" + identifier));
        }catch (Exception ex){
            throw new FlightIdentifierException();
        }
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
                .orElseThrow(() -> new FlightQueryException("Cannot find most recent flight"));
    }

    public List<Flight> findFlightsByAirport(String icao, boolean isDeparture, AirportService service){
        List<Flight> flights = isDeparture ? flightRepository.findFlightsByDeparture(icao)
                : flightRepository.findFlightsByDestination(icao);
        if(!flights.isEmpty()) return flights;
        if(!service.airportExists(icao)){
            throw new AirportNotFoundException(icao + " does not exist");
        }
        throw new FlightQueryException("No flights are scheduled " + (isDeparture ? "from " : "to ") + icao);
    }

    public List<Flight> findFlightsByDate(String date){
        if(!DateTimeUtils.isValid(date)) throw new DateFormatException();
        List<Flight> flights = flightRepository.findFlightsByDate(DateTimeUtils.stringToSQLDate(date));
        if(flights.isEmpty()) throw new FlightQueryException("No flights are scheduled on " + date);
        return flights;
    }

    public List<FlightTimeTable> findFlightsByDateRange(String start, String end){
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
        return FlightTimeTable.generate(flightsOnDate);
    }

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
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
