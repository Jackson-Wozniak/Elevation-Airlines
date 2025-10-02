using backend.Domain.airport.Entity;

namespace backend.Domain.airport.Dto;

public class AirportDto
{
    public string AirportCode { get; set; }
    public string AirportName { get; set; }
    public double Latitude { get; set; }
    public double Longitude { get; set; }
    public string City { get; set; }
    public string State { get; set; }
    public string Country { get; set; }
    public double EconomicValuePercentile { get; set; }
    public string MarketType { get; set; }

    public AirportDto(Airport airport)
    {
        AirportCode = airport.AirportCode;
        AirportName = airport.AirportName;
        Latitude = airport.Latitude;
        Longitude = airport.Longitude;
        City = airport.City;
        State = airport.State;
        Country = airport.Country;
        EconomicValuePercentile = airport.EconomicValuePercentile;
        MarketType = airport.MarketType.ToString();
    }
}