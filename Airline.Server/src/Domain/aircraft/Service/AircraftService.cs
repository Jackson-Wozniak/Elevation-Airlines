using Airline.Server.Core.Data;
using Airline.Server.Domain.aircraft.Entity;

namespace Airline.Server.Domain.aircraft.Service;

public class AircraftService(ApplicationDbContext context)
{
    public List<Aircraft> GetAllAircraft()
    {
        return context.Aircraft.ToList();
    }
    
    public void DeleteAllAircraft()
    {
        context.Aircraft.RemoveRange(context.Aircraft);
        context.SaveChanges();
    }

    public void SaveAircraft(List<Aircraft> aircraft)
    {
        context.Aircraft.AddRange(aircraft);
        context.SaveChanges();
    }
}