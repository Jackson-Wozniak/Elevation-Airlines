using Airline.Server.Domain.routenetwork.Entity;

namespace Airline.Server.Api.Dtos;

public class NetworkMapDto
{
    private Dictionary<AirportDto, List<AirportDto>> _connections = [];

    public List<AirportConnectionDto> Connections
    {
        get
        {
            return _connections
                .Select(c => new AirportConnectionDto(c))
                .ToList();
        }
    }

    public NetworkMapDto(List<NetworkedRoute> routes)
    {
        foreach (var route in routes)
        {
            var dep = new AirportDto(route.Route.Departure);
            var dest = new AirportDto(route.Route.Destination);
            if (!_connections.ContainsKey(dep)) _connections[dep] = new List<AirportDto>();
            _connections[dep].Add(dest);
        }
    }
}