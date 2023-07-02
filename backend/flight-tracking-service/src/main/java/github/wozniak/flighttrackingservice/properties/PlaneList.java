package github.wozniak.flighttrackingservice.properties;

import github.wozniak.flighttrackingservice.entity.Plane;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaneList {

    private static final Random random = new Random();
    private static final List<PlaneModel> planeModels = List.of(
            new PlaneModel("B737", 189, 20, 449, 3500),
            new PlaneModel("B787", 246, 40, 492, 8786)
    );

    public static List<Plane> getDefaultPlanes(){
        //fleet of 30 planes
        List<Plane> defaultPlanes = new ArrayList<>();
        List<Integer> usedCallSigns = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            int callSign = random.nextInt(10, 999);
            if(usedCallSigns.contains(callSign)){
                i--;
                continue;
            }
            usedCallSigns.add(callSign);
            defaultPlanes.add(new Plane("ELV" + callSign, planeModels.get(random.nextInt(planeModels.size()))));
        }
        return defaultPlanes;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class PlaneModel{
        private String model;
        private int seatingCapacity;
        private int luxurySeats;
        private int cruisingSpeedKnots;
        private int rangeMiles;
    }
}
