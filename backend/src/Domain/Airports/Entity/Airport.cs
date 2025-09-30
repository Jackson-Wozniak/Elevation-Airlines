using backend.Core.Entity;

namespace backend.Domain.Airports.Entity;

public class Airport : BaseEntity
{
    public string AirportCode { get; set; } = "";
    public string AirportName { get; set; } = "";
    public double Latitude { get; set; }
    public double Longitude { get; set; }
    public string City { get; set; } = "";
    public string State { get; set; } = "";
    public string Country { get; set; } = "";
    public double EconomicValuePercentile { get; set; }
    
    public Airport(){ }

    public Airport(string airportCode, string airportName, 
        double latitude, double longitude, 
        string city, string state, string country, 
        double economicValuePercentile)
    {
        AirportCode = airportCode;
        AirportName = airportName;
        Latitude = latitude;
        Longitude = longitude;
        City = city;
        State = state;
        Country = country;
        EconomicValuePercentile = economicValuePercentile;
    }
}