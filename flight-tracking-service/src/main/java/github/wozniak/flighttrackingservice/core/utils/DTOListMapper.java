package github.wozniak.flighttrackingservice.core.utils;

import github.wozniak.flighttrackingservice.flight_management.dto.FlightDTO;
import github.wozniak.flighttrackingservice.flight_management.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.flight_management.dto.TimeTableDTO;
import github.wozniak.flighttrackingservice.flight_management.entity.Flight;
import github.wozniak.flighttrackingservice.flight_management.model.TimeTable;

import java.util.List;

public class DTOListMapper {

    public static List<FlightDTO> flightDTOs(List<Flight> flight){
        return flight.stream().map(FlightDTO::new).toList();
    }

    public static List<FlightSummaryDTO> flightSummaryDTOs(List<Flight> flight){
        return flight.stream().map(FlightSummaryDTO::new).toList();
    }

    public static List<TimeTableDTO> timeTableDTOS(List<TimeTable> timeTables){
        return timeTables.stream().map(TimeTableDTO::new).toList();
    }
}
