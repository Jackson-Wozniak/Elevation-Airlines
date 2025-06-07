package internal.parser;

import internal.parser.files.PassengerCSVFile;

import java.io.IOException;

public class AirportParser {
    public static void main( String[] args ) throws IOException {
        PassengerCSVFile file = PassengerCSVFile.getInstance();

        System.out.println(file.getPassengersPerAirport());
    }
}
