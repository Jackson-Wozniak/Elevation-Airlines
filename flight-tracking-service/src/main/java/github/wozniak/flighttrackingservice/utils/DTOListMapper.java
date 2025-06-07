package github.wozniak.flighttrackingservice.utils;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.dto.TimeTableDTO;
import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.model.TimeTable;

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
