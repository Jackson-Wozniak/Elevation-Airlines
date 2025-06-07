package internal.parser;

import internal.parser.files.AirportCSVFile;
import internal.parser.files.PassengerCSVFile;
import internal.parser.files.RunwayCSVFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AirportParser {
    public static void main( String[] args ) throws IOException {
//        PassengerCSVFile file = PassengerCSVFile.getInstance();
//
//        Map<String, Integer> passengers = file.getPassengersPerAirport();
//        RunwayCSVFile file2 = RunwayCSVFile.getInstance();
//
//        AtomicInteger count = new AtomicInteger(0);
//
//        passengers.forEach((code, passengerCount) -> {
//            if(file2.getRunwaysPerAirport().containsKey("K" + code)) count.incrementAndGet();
//            System.out.println(code + ": " + passengerCount);
//        });
//        System.out.println(file2.getRunwaysPerAirport().size());
//        System.out.println(passengers.size());
//        System.out.println(count.get());
        AirportCSVFile file3 = AirportCSVFile.getInstance();

        file3.getAirports().forEach(airport -> {
            System.out.println(airport.getCode());
        });
    }
}
