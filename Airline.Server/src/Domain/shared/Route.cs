using System.ComponentModel.DataAnnotations.Schema;
using Airline.Server.Domain.airport.Entity;
using Airline.Server.Domain.airport.Utils;

namespace Airline.Server.Domain.shared;

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