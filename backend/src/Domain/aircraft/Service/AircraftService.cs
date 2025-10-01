using backend.Core.Data;
using backend.Domain.aircraft.Entity;

namespace backend.Domain.aircraft.Service;

public class AircraftService(ApplicationDbContext context)
{
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