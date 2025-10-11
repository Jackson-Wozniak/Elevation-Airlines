using System.Collections.Immutable;
using Airline.Server.Domain.airport.Entity;
using Airline.Server.Domain.routenetwork.Entity;

namespace Airline.Server.Domain.routenetwork.Dto;

public class RouteNetworkDto
{
    public int StatesServiced { get; set; }
    public int AverageRoutesPerWeek { get; set; }
    public List<NetworkedRouteDto> Routes { get; set; }

    public RouteNetworkDto(List<NetworkedRoute> routes)
    {
        AverageRoutesPerWeek = routes
            .Select(r => r.PlannedWeeklyFlights)
            .Sum();
        var uniqueStates = new HashSet<string>();
        foreach (var r in routes)
        {
            uniqueStates.Add(r.Route.Departure.State);
            uniqueStates.Add(r.Route.Destination.State);
        }
        StatesServiced = uniqueStates.Count;
        Routes = routes.Select(r => new NetworkedRouteDto(r)).ToList();
    }
}