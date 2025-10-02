using backend.Domain.airport.Entity;

namespace backend.Domain.shared;

public class Route
{
    public Airport Departure { get; set; }
    public Airport Destination { get; set; }

    protected Route() { }

    public Route(Airport departure, Airport destination)
    {
        Departure = departure;
        Destination = destination;
    }
}