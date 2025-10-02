using System.ComponentModel.DataAnnotations.Schema;
using backend.Domain.airport.Entity;
using backend.Domain.airport.Utils;

namespace backend.Domain.shared;

public class Route
{
    public Airport Departure { get; set; }
    public Airport Destination { get; set; }
    [NotMapped]
    public double DistanceNauticalMiles => Departure.NauticalMilesTo(Destination);

    protected Route() { }

    public Route(Airport departure, Airport destination)
    {
        Departure = departure;
        Destination = destination;
    }
}