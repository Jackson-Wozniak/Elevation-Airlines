package github.wozniak.flighttrackingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Getter
@Setter
public class PlaneModel{
    private String model;
    private int seatingCapacity;
    private int luxurySeats;
    private int cruisingSpeedKnots;
    private int rangeMiles;

    public static PlaneModel randomPlane(List<PlaneModel> models, Random random){
        int percent = random.nextInt(100);
        if(percent < 40){
            return models.get(0);
        }
        if(percent < 60){
            return models.get(1);
        }
        if(percent < 80){
            return models.get(2);
        }
        return models.get(3);
    }
}
