using backend.Core.Data;
using backend.Core.Settings;
using backend.Domain.airport.Entity;
using backend.Domain.airport.Service;
using backend.Domain.routenetwork.Entity;
using backend.Domain.routenetwork.Enum;
using backend.Domain.routenetwork.Service;
using Microsoft.Extensions.Options;

namespace backend.Engine.Scheduling.Service;

public class NetworkPlannerService(
    AirportService airportService,
    IOptions<SimulationSettings> options,
    NetworkedRouteService networkedRouteService)
{
    private readonly SimulationSettings _simulationSettings = options.Value;
    
    /*
    Current Network Methodology:
        First, we get the primary hub for the airline. Next, we sort the airports
        into a dictionary by state. Finally, we create a new NetworkedRoute to
        the top airport in each state by economic percentile.
    */
    public List<NetworkedRoute> CreateRouteNetwork()
    {
        var airports = airportService.FindAirports();
        var hub = airports.SingleOrDefault(a => 
            a.AirportCode.Equals(_simulationSettings.PrimaryHub));
        if (hub == null) throw new Exception($"Error configuring network: No hub {_simulationSettings.PrimaryHub}");

        airports = airports.Where(a => !a.AirportCode.Equals(hub.AirportCode)).ToList();

        Dictionary<string, List<Airport>> airportsByState = airports
            .GroupBy(a => a.State)
            .ToDictionary(
                g => g.Key,
                g => g.ToList()
            );

        List<NetworkedRoute> routes = [];

        foreach (KeyValuePair<string, List<Airport>> entry in airportsByState)
        {
            var sortedByEconomySize = entry.Value
                .OrderByDescending(a => a.EconomicValuePercentile)
                .Take(2)
                .ToList();
            sortedByEconomySize.ForEach(a =>
            {
                var market = RouteMarketTypeUtils.MapFromAirports(hub, a, true, true);
                routes.Add(new NetworkedRoute(hub, 
                    a, 7, market));
                routes.Add(new NetworkedRoute(a, 
                    hub, 7, RouteMarketType.ReturnToHub));
            });
        }
        networkedRouteService.SaveNetworkedRoute(routes);
        
        return routes;
    }
}