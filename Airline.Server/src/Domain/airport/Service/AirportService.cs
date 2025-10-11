using Airline.Server.Core.Data;
using Airline.Server.Core.Exception.types;
using Airline.Server.Domain.airport.Entity;

namespace Airline.Server.Domain.airport.Service;

public class AirportService(ApplicationDbContext context)
{
    public List<Airport> FindAirports()
    {
        return context.Airports.ToList();
    }

    public Airport FindAirportByCode(string code)
    {
        var airport = context.Airports.SingleOrDefault(a => a.AirportCode.Equals(code));
        if (airport is null) throw new NotFoundException($"Cannot find airport {code}", "");
        return airport;
    }
    
    public void DeleteAllAirports()
    {
        context.Airports.RemoveRange(context.Airports);
        context.SaveChanges();
    }

    public void SaveAirport(List<Airport> aircraft)
    {
        context.Airports.AddRange(aircraft);
        context.SaveChanges();
    }
}