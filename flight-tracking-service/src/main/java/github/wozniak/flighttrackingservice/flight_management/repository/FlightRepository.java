package github.wozniak.flighttrackingservice.flight_management.repository;

import github.wozniak.flighttrackingservice.flight_management.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    String FIND_CALL_SIGN = "SELECT * FROM flights f WHERE f.call_sign=:callSign";
    String FIND_DEPARTURE = "SELECT * FROM flights f WHERE f.departure_icao=:code";
    String FIND_DESTINATION = "SELECT * FROM flights f WHERE f.destination_icao=:code";
    String FIND_DATES = "SELECT * FROM flights f WHERE DATE(f.scheduled_takeoff_time_and_date)=:date";

    @Query(value = FIND_CALL_SIGN, nativeQuery = true)
    List<Flight> findFlightsByCallSign(String callSign);

    @Query(value = FIND_DEPARTURE, nativeQuery = true)
    List<Flight> findFlightsByDeparture(String code);

    @Query(value = FIND_DESTINATION, nativeQuery = true)
    List<Flight> findFlightsByDestination(String code);

    @Query(value = FIND_DATES, nativeQuery = true)
    List<Flight> findFlightsByDate(Date date);
}
