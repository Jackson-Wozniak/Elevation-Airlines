using Airline.Server.Domain.routenetwork.Entity;

namespace Airline.Server.Api.Dtos;

public class NetworkedRouteDto
{
    public AirportDto Departure { get; set; }
    public AirportDto Destination { get; set; }
    public double DistanceNauticalMiles { get; set; }
    public int PlannedWeeklyFlights { get; set; }
    public string RouteMarketType { get; set; }

    public NetworkedRouteDto(NetworkedRoute route)
    {
        Departure = new AirportDto(route.Route.Departure);
        Destination = new AirportDto(route.Route.Destination);
        DistanceNauticalMiles = route.Route.DistanceNauticalMiles;
        PlannedWeeklyFlights = route.PlannedWeeklyFlights;
        RouteMarketType = route.MarketType.ToString();
    }
}