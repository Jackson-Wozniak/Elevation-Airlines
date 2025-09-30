package internal.parser.objects;

import internal.parser.objects.csv.CityCSVObject;
import internal.parser.objects.csv.CityStatsCSVObject;

import java.util.*;

public class City {
    private final String name;
    private final String state;
    private final String county;
    private final String population;
    private final String ranking;
    private final String connectedMarkets;
    private final long averagePassengersQ1;
    private final long averagePassengersQ2;
    private final long averagePassengersQ3;
    private final long averagePassengersQ4;
    private final double passengersCAGR;

    private City(String name, String state, String county,
                      String population, String ranking,
                      String connectedMarkets, long averagePassengersQ1,
                      long averagePassengersQ2, long averagePassengersQ3,
                       long averagePassengersQ4, double passengersCAGR) {
        this.name = name;
        this.state = state;
        this.county = county;
        this.population = population;
        this.ranking = ranking;
        this.connectedMarkets = connectedMarkets;
        this.averagePassengersQ1 = averagePassengersQ1;
        this.averagePassengersQ2 = averagePassengersQ2;
        this.averagePassengersQ3 = averagePassengersQ3;
        this.averagePassengersQ4 = averagePassengersQ4;
        this.passengersCAGR = passengersCAGR;
    }

    public static City create(CityCSVObject city, Map<String, CityStatsCSVObject> stats){
        if(stats.isEmpty()){
            return new City(city.getName(), city.getState(), city.getCounty(),
                    city.getPopulation(), city.getRanking(), "0",
                    0, 0,0,0, 0);
        }
        //format of stats key is -> Q1 2021, so we can split by the space to get quarter/year
        Map<Integer, CityStatsCSVObject> q1 = map(stats, 1);
        Map<Integer, CityStatsCSVObject> q2 = map(stats, 2);
        Map<Integer, CityStatsCSVObject> q3 = map(stats, 3);
        Map<Integer, CityStatsCSVObject> q4 = map(stats, 4);

        long q1Avg = quarterAverage(q1.values());
        long q2Avg = quarterAverage(q2.values());
        long q3Avg = quarterAverage(q3.values());
        long q4Avg = quarterAverage(q4.values());

        double totalCAGR = Math.round((quarterCAGR(q1) + quarterCAGR(q2) + quarterCAGR(q3)
                + quarterCAGR(q4)) * 100.00) / 100.00;

        return new City(city.getName(), city.getState(), city.getCounty(),
                city.getPopulation(), city.getRanking(), "0",
                q1Avg, q2Avg,q3Avg,q4Avg, totalCAGR);
    }

    private static Map<Integer, CityStatsCSVObject> map(Map<String, CityStatsCSVObject> stats, int quarter){
        Map<Integer, CityStatsCSVObject> quarters = new HashMap<>();
        stats.forEach((key, value) -> {
            if(key.split(" ")[0].equals("Q" + quarter)){
                int year = Integer.parseInt(key.split(" ")[1]);
                if(!quarters.containsKey(year)) quarters.put(year, value);
            }
        });
        return quarters;
    }

    private static long quarterAverage(Collection<CityStatsCSVObject> stats){
        if(stats.isEmpty()) return 0;
        return (long) ((double) stats.stream()
                .mapToLong(c -> Long.parseLong(c.getPassengerCount())).sum())
                / stats.size();
    }

    private static double quarterCAGR(Map<Integer, CityStatsCSVObject> stats){
        if(stats.keySet().size() < 2) return 0.0;
        long minYearPassengers = Long.parseLong(stats.get(Collections.min(stats.keySet())).getPassengerCount());
        long maxYearPassengers = Long.parseLong(stats.get(Collections.max(stats.keySet())).getPassengerCount());

        double rate = (Math.pow(((double) maxYearPassengers / minYearPassengers), (1.0 / stats.size()))) - 1;

        return rate * 100.0;
    }

    @Override
    public String toString(){
        return this.name + "," +
                this.state + "," +
                this.county + "," +
                this.population + "," +
                this.ranking + "," +
                this.connectedMarkets + "," +
                this.averagePassengersQ1 + "," +
                this.averagePassengersQ2 + "," +
                this.averagePassengersQ3 +"," +
                this.averagePassengersQ4 + "," +
                this.passengersCAGR +'\n';
    }

    public String getKey(){
        String key = this.name + "," + this.state;
        key = key.replace(" ", "").replace(".", "");
        return key;
    }

    public String getCountyKey(){
        String key = this.county + "," + this.state;
        key = key.replace(" ", "").replace(".", "");
        return key;
    }
}
