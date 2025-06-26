package github.wozniak.flighttrackingservice.airline_management.network_planner.configuration;

import github.wozniak.flighttrackingservice.airline_management.flight_manager.entity.Route;
import github.wozniak.flighttrackingservice.airline_management.network_planner.entity.ServicedRoute;
import github.wozniak.flighttrackingservice.airline_management.network_planner.enums.RouteFrequency;
import github.wozniak.flighttrackingservice.airline_management.network_planner.service.NetworkService;
import github.wozniak.flighttrackingservice.airline_management.network_planner.service.ServicedRouteService;
import github.wozniak.flighttrackingservice.airline_management.network_planner.utils.NetworkEconomicUtils;
import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.enums.TimeOfDay;
import github.wozniak.flighttrackingservice.core.properties.ElevationAirlineProperties;
import github.wozniak.flighttrackingservice.core.service.AirportService;
import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.service.CityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class ServicedRouteConfiguration {
    private final CityService cityService;
    private final AirportService airportService;
    private final ServicedRouteService servicedRouteService;
    private final NetworkService networkService;
    private static final Logger logger = LoggerFactory.getLogger(ServicedRouteConfiguration.class);

    public void configureRouteNetwork(){
        throw new RuntimeException("configureRouteNetwork() NOT IMPLEMENTED!");
    }

    //TODO: this should be a lot more intelligent in its route generation
    public void configureRouteNetworkUnfinished(){
        logger.info("Configuring serviced routes with unfinished implementation!");
        Map<String, City> cities = cityService.findAllCitiesAndCounties().stream()
                .collect(Collectors.toMap(city -> city.getId().toUpperCase().replace(" ", ""), city -> city));
        List<Airport> airports = airportService.findAllAirports();

        List<Airport> sortedAirports = NetworkEconomicUtils.sortAirportsByDemand(cities, airports);

        List<ServicedRoute> servicedRoutes = new ArrayList<>();
        Airport hub = networkService.findPrimaryHub();
        for(int i = 0; i < ElevationAirlineProperties.SERVICED_ROUTE_COUNT; i++){
            RouteFrequency frequency = NetworkEconomicUtils.routeFrequency(cities.get(sortedAirports.get(i).getCityKey().toUpperCase().replace(" ", "")));
            servicedRoutes.add(new ServicedRoute(1, frequency, TimeOfDay.ANY,
                    new Route(hub, sortedAirports.get(i))));
        }
        servicedRouteService.saveDefaultRoutes(servicedRoutes);
    }
}
