package github.wozniak.flighttrackingservice.utils;

import github.wozniak.flighttrackingservice.dto.FlightDTO;
import github.wozniak.flighttrackingservice.dto.FlightInfoDTO;
import github.wozniak.flighttrackingservice.dto.FlightSummaryDTO;
import github.wozniak.flighttrackingservice.dto.TimeTableSummaryDTO;
import github.wozniak.flighttrackingservice.entity.Flight;
import github.wozniak.flighttrackingservice.model.TimeTable;

import java.util.List;

public class DTOListMapper {

    public static List<FlightDTO> flightDTOs(List<Flight> flight){
        return flight.stream().map(Flight::getDTO).toList();
    }

    public static List<FlightSummaryDTO> flightSummaryDTOs(List<Flight> flight){
        return flight.stream().map(Flight::getDTOSummary).toList();
    }

    public static List<FlightInfoDTO> flightInfoDTOs(List<Flight> flight){
        return flight.stream().map(FlightInfoDTO::new).toList();
    }

    public static List<TimeTableSummaryDTO> timeTableSummaryDTOS(List<TimeTable> timeTables){
        return timeTables.stream().map(TimeTable::getDTOSummary).toList();
    }
}
