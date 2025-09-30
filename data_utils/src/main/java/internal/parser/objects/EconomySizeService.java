package internal.parser.objects;

import internal.parser.utils.CSVReaderUtils;

import java.util.List;

import static internal.parser.utils.CSVReaderUtils.getPercentile;

public class EconomySizeService {
    List<Double> airportPassengers;
    List<Double> countyGdps;
    List<Double> countyGdpCagrs;
    List<Double> cityPopulations;
    List<Double> cityPassengers;

    public EconomySizeService(List<Airport> airports, List<City> cities, List<County> counties){
        this.airportPassengers = airports.stream().mapToDouble(Airport::getPassengerCount)
                .filter(val -> val > 0).boxed().toList();
        this.countyGdps = counties.stream().mapToDouble(County::gdp)
                .filter(val -> val > 0).boxed().toList();
        this.countyGdpCagrs = counties.stream().mapToDouble(County::getCAGR)
                .filter(val -> val > 0).boxed().toList();
        this.cityPopulations = cities.stream().mapToDouble(City::getPopulation)
                .filter(val -> val > 0).boxed().toList();
        this.cityPassengers = cities.stream().mapToDouble(City::passengerSum)
                .filter(val -> val > 0).boxed().toList();
    }

    public double calculateEconomySize(Airport airport, City city){
        return calculateEconomySize(airport, city, null);
    }

    public double calculateEconomySize(Airport airport, City city, County county){
        /*
        Method:
            - find percentiles for the major stats
            - get city rank, connectedMarkets
         */
        double airportPassengerPercentile = getPercentile(airport.getPassengerCount(), airportPassengers);
        double cityPopulationPercentile = getPercentile(city.getPopulation(), cityPopulations);
        double cityPassengerPercentile = getPercentile(city.passengerSum(), cityPassengers);
        if(county == null) return CSVReaderUtils.averageRemoveZeroes(List.of(
                airportPassengerPercentile, cityPopulationPercentile, cityPassengerPercentile));
        double countyGdpPercentile = getPercentile(county.gdp(), countyGdps);
        double countyGdpCagrPercentile = getPercentile(county.getCAGR(), countyGdpCagrs);
        return CSVReaderUtils.averageRemoveZeroes(List.of(
                airportPassengerPercentile, cityPopulationPercentile, cityPassengerPercentile,
                countyGdpPercentile, countyGdpCagrPercentile));
    }
}
