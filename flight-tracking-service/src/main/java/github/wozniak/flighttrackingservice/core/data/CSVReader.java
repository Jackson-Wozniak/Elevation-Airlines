package github.wozniak.flighttrackingservice.core.data;

import github.wozniak.flighttrackingservice.core.entity.Airport;
import github.wozniak.flighttrackingservice.core.entity.PlaneModel;
import github.wozniak.flighttrackingservice.economics.entity.City;
import github.wozniak.flighttrackingservice.economics.entity.County;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class CSVReader<T> {
    protected List<T> read() throws IOException {
        throw new IOException("No implementation of reader!");
    }

    protected List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).toList();
    }

    protected int safeIntegerParse(String value){
        try{
            return Integer.parseInt(value);
        }catch(Exception ex){
            //TODO: manually go through each digit if needed
            return 0;
        }
    }

    protected long safeLongParse(String value){
        try{
            return Long.parseLong(value);
        }catch(Exception ex){
            //TODO: manually go through each digit if needed
            return 0;
        }
    }

    protected double safeDoubleParse(String value){
        try{
            return Double.parseDouble(value);
        }catch(Exception ex){
            //TODO: manually go through each digit if needed
            return 0.0;
        }
    }

    public static List<Airport> airports() throws IOException{
        return AirportReader.getInstance().read();
    }

    public static List<PlaneModel> planeModels() throws IOException{
        return PlaneModelReader.getInstance().read();
    }

    public static List<City> cities(Map<String, County> counties) throws IOException{
        return CityReader.getInstance().read(counties);
    }

    public static List<County> counties() throws IOException{
        return CountyReader.getInstance().read();
    }
}
