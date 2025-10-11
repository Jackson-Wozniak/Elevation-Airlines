using Airline.Server.Domain.airport.Entity;

namespace Airline.Server.Domain.airport.Dto;

public class AirportDto
{
    public string AirportCode { get; }
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
        if (airport.AirportCode == null) throw new Exception();
        
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

    public override bool Equals(object? obj)
    {
        if (obj == null) return false;
        if(obj is not AirportDto other) return false;

        return AirportCode.Equals(other.AirportCode);
    }

    public override int GetHashCode()
    {
        return AirportCode.GetHashCode();
    }
}