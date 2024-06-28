package github.wozniak.flighttrackingservice.properties;

import github.wozniak.flighttrackingservice.entity.Plane;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlaneListTest {

    @Test
    void callSignsFollowsPatternTest(){
        PlaneList.getDefaultPlanes().forEach(plane -> {
            assertEquals("ELV",  plane.getCallSign().substring(0, 3));
            int callSignNumber = Integer.parseInt(plane.getCallSign().substring(3));
            assertTrue(callSignNumber > 0 && callSignNumber <= 999);
        });
    }

    @Test
    void noDuplicateCallSigns(){
        List<String> callSigns = PlaneList.getDefaultPlanes(999).stream().map(Plane::getCallSign).toList();
        Set<String> uniqueCallSigns = new HashSet<>(callSigns);
        assertEquals(callSigns.size(), uniqueCallSigns.size());
        assertEquals(999, callSigns.size());
    }

}