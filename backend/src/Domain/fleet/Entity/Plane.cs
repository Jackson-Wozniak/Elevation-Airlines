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
    
    public Plane() { }

    public Plane(string callSign, Aircraft aircraft, PlaneStatus status)
    {
        CallSign = callSign;
        Aircraft = aircraft;
        Status = status;
    }
}