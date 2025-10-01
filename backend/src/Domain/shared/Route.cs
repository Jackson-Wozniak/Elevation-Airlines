using backend.Domain.airport.Entity;

namespace backend.Domain.shared;

public class Route
{
    public Airport Departure { get; set; }
    public Airport Destination { get; set; }
}