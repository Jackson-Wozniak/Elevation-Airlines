using Airline.Server.Core.Entity;
using Airline.Server.Domain.aircraft.Entity;
using Airline.Server.Domain.fleet.Enums;
using Airline.Server.Domain.flight.Entity;

namespace Airline.Server.Domain.fleet.Entity;

public class Plane : BaseEntity
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

    public override bool Equals(object? obj)
    {
        if (obj is null) return false;
        if (obj is not Plane p) return false;
        return p.Id == Id;
    }

    public override int GetHashCode()
    {
        return Id.GetHashCode();
    }
}