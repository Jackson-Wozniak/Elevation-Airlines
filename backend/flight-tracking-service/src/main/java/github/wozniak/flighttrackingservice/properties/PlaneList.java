package github.wozniak.flighttrackingservice.properties;

import github.wozniak.flighttrackingservice.entity.Plane;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PlaneList {

    private static final Random random = new Random();
    private static final List<PlaneModel> planeModels = List.of(
            //~40% of fleet
            new PlaneModel("B737", 189, 20, 449, 3500),
            //~20% of fleet
            new PlaneModel("B787", 246, 40, 492, 8786),
            //~20% of fleet
            new PlaneModel("B757", 220, 30, 459, 4488),
            //~20% of fleet
            new PlaneModel("A320neo", 194, 20, 453, 3500)
    );

    public static List<Plane> getDefaultPlanes(){
        //fleet of 30 planes
        List<Plane> defaultPlanes = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>(IntStream.range(1, 1000).boxed().toList());
        for(int i = 0; i < 30; i++){
            int callSign = nums.get(random.nextInt(nums.size()));
            nums.remove(Integer.valueOf(callSign));
            defaultPlanes.add(new Plane("ELV" + callSign, randomPlane()));
        }
        return defaultPlanes;
    }

    public static List<Plane> getDefaultPlanes(int planes){
        if(planes > 999) planes = 999;
        List<Plane> defaultPlanes = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>(IntStream.range(1, 1000).boxed().toList());
        for(int i = 0; i < planes; i++){
            int callSign = nums.get(random.nextInt(nums.size()));
            nums.remove(Integer.valueOf(callSign));
            defaultPlanes.add(new Plane("ELV" + callSign, randomPlane()));
        }
        return defaultPlanes;
    }

    private static PlaneModel randomPlane(){
        int percent = random.nextInt(100);
        if(percent < 40){
            return planeModels.get(0);
        }
        if(percent < 60){
            return planeModels.get(1);
        }
        if(percent < 80){
            return planeModels.get(2);
        }
        return planeModels.get(3);

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
