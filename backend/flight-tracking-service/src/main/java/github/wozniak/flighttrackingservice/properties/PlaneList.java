package github.wozniak.flighttrackingservice.properties;

import github.wozniak.flighttrackingservice.entity.Plane;

import java.util.List;
import java.util.Random;

public class PlaneList {

    private static final Random random = new Random();

    public static List<Plane> getDefaultPlanes(){
        return List.of();
    }
}
