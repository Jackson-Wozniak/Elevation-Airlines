using System.ComponentModel.DataAnnotations.Schema;
using backend.Domain.airport.Entity;

namespace backend.Domain.shared;

public class Route
{
    public Airport Departure { get; set; }
    public Airport Destination { get; set; }
    [NotMapped]
    public double DistanceNauticalMiles { get; set; }

    protected Route() { }

    public Route(Airport departure, Airport destination)
    {
        Departure = departure;
        Destination = destination;
        DistanceNauticalMiles = 0;
    }
}