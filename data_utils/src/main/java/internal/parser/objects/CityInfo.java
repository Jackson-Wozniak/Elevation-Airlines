package internal.parser.objects;

public class CityInfo {
    private final String name;
    private final String state;
    private final String county;
    private final String population;
    private final String ranking;

    public CityInfo(String name, String state, String county, String population, String ranking) {
        this.name = name;
        this.state = state;
        this.county = county;
        this.population = population;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public String getPopulation() {
        return population;
    }

    public String getRanking() {
        return ranking;
    }
}
