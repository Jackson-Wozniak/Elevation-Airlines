using Airline.Server.Domain.airport.Dto;
using Airline.Server.Domain.flight.Entity;

namespace Airline.Server.Domain.flight.Dto;

public class FlightPlanDto
{
    public AirportDto Departure { get; set; }
    public AirportDto Destination { get; set; }
    public double DistanceNauticalMiles { get; set; }

    public FlightPlanDto(FlightPlan plan)
    {
        Departure = new AirportDto(plan.Route.Departure);
        Destination = new AirportDto(plan.Route.Destination);
        DistanceNauticalMiles = plan.Route.DistanceNauticalMiles;
    }
}