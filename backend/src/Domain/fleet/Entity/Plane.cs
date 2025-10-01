using backend.Domain.aircraft.Entity;
using backend.Domain.fleet.Enums;
using backend.Domain.flight.Entity;

namespace backend.Domain.fleet.Entity;

public class Plane
{
    public string CallSign { get; set; }
    public Aircraft Aircraft { get; set; }
    public PlaneStatus Status { get; set; }

    public List<Flight> ScheduledFlights { get; set; } = [];
}