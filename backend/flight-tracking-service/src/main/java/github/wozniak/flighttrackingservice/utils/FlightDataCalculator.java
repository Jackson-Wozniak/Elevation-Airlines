package github.wozniak.flighttrackingservice.utils;

import github.wozniak.flighttrackingservice.entity.Airport;
import github.wozniak.flighttrackingservice.entity.Plane;

import java.text.DecimalFormat;

public class FlightDataCalculator {

    private static final DecimalFormat formatter = new DecimalFormat("#.##");

    //uses Haversine formula based on coordinates
    public static int getFlightMiles(Airport departure, Airport destination){
        return 0;
    }

    public static double getFlightHours(int flightMiles, Plane plane){
        if(plane.getCruisingSpeedKnots() == 0) throw new RuntimeException("Plane must have positive cruise speed");
        double time = flightMiles / knotsToMPH(plane.getCruisingSpeedKnots());
        return Double.parseDouble(String.format("%.02f", time));
    }

    public static double knotsToMPH(int knots){
        return Double.parseDouble(String.format("%.02f", knots * 1.15078));
    }
}
