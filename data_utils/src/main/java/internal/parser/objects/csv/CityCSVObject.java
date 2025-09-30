package internal.parser.objects.csv;

public class CityCSVObject {
    private final String name;
    private final String state;
    private final String county;
    private final String population;
    private final String ranking;

    public CityCSVObject(String name, String state, String county, String population, String ranking) {
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

    public String getKey(){
        String key = this.name + "," + this.state;
        key = key.replace(" ", "").replace(".", "");
        return key;
    }
}
