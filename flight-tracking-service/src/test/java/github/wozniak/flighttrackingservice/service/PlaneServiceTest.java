package github.wozniak.flighttrackingservice.service;

import github.wozniak.flighttrackingservice.airline_management.fleet_manager.entity.Plane;
import github.wozniak.flighttrackingservice.airline_management.fleet_manager.service.PlaneService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PlaneServiceTest {

    private final PlaneService planeService;

    @Autowired
    public PlaneServiceTest(PlaneService planeService){
        this.planeService = planeService;
    }

    @Test
    void allPlanesUsedCreatesEmptyList(){
        assertEquals(0, planeService.findUnusedPlanes(planeService.findAllPlanes()).size());
    }

    @Test
    void unusedPlaneSortingTest(){
        List<Plane> planes1 = planeService.findAllPlanes();
        assertNotEquals(0, planes1.size());

        //split list into 2 (so 1/2 the list is unused)
        List<Plane> planes2 = planeService.findAllPlanes().subList(planes1.size() / 2, planes1.size());
        planes1 = planes1.subList(0, planes1.size() / 2);

        List<Plane> unusedPlanes = planeService.findUnusedPlanes(planes1);
        unusedPlanes.forEach(plane -> assertTrue(planes2.contains(plane)));
    }

}