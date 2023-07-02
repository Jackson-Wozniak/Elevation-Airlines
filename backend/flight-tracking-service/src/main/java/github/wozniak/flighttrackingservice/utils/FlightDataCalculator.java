package github.wozniak.flighttrackingservice.utils;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Plane;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.Random;

public class FlightDataCalculator {

    private static final Random random = new Random();

    //uses Haversine formula based on coordinates
    public static int getFlightMiles(Airport departure, Airport destination){
        double depLat = Math.toRadians(departure.getLatitude());
        double depLong = Math.toRadians(departure.getLongitude());
        double destLat = Math.toRadians(destination.getLatitude());
        double destLong = Math.toRadians(destination.getLongitude());
        double dLat = depLat - destLat;
        double dLong = depLong - destLong;

        double a = (Math.pow(Math.sin(dLat / 2), 2)) + Math.cos(depLat) * Math.cos(destLat)
                * (Math.pow(Math.sin(dLong / 2), 2));
        double c = 2 * Math.asin(Math.sqrt(a));
        return (int) (c * 3958.8);
    }

    public static double getFlightHours(int flightMiles, Plane plane){
        if(plane.getCruisingSpeedKnots() == 0) throw new RuntimeException("Plane must have positive cruise speed");
        double time = flightMiles / knotsToMPH(plane.getCruisingSpeedKnots());
        return Double.parseDouble(String.format("%.02f", time));
    }

    public static double knotsToMPH(int knots){
        return Double.parseDouble(String.format("%.02f", knots * 1.15078));
    }

    //returns time in format
    public static LocalTime createTimeOfFlight(){
        int hour = random.nextInt(24);
        int minute = random.nextBoolean() ? 30 : 0;
        return LocalTime.of(hour, minute);
    }
}
