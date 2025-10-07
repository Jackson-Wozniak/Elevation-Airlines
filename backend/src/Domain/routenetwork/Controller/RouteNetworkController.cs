using backend.Domain.routenetwork.Dto;
using backend.Domain.routenetwork.Service;
using Microsoft.AspNetCore.Mvc;

namespace backend.Domain.routenetwork.Controller;

[ApiController]
[Route($"api/[controller]")]
public class RouteNetworkController(
    NetworkedRouteService networkedRouteService) : ControllerBase
{
    [HttpGet]
    public ActionResult<RouteNetworkDto> GetRouteNetwork()
    {
        return Ok(new RouteNetworkDto(networkedRouteService.GetNetworkedRoutes()));
    }
    
    [HttpGet("{airport}")]
    public ActionResult<RouteNetworkDto> GetRouteNetworkByAirport(string airport)
    {
        return Ok(new RouteNetworkDto(networkedRouteService.GetNetworkedRoutesFrom(airport)));
    }
    
    [HttpGet("Routes")]
    public ActionResult<IEnumerable<NetworkedRouteDto>> GetRoutes()
    {
        return Ok(networkedRouteService.GetNetworkedRoutes()
            .Select(r => new NetworkedRouteDto(r)));
    }

    [HttpGet("Routes/{airport}")]
    public ActionResult<IEnumerable<NetworkedRouteDto>> GetRoutesByAirport(string airport)
    {
        return Ok(networkedRouteService.GetNetworkedRoutesFrom(airport)
            .Select(r => new NetworkedRouteDto(r)));
    }

    [HttpGet("Map")]
    public ActionResult<List<AirportConnectionDto>> GetNetworkMap()
    {
        return Ok(new NetworkMapDto(networkedRouteService
            .GetNetworkedRoutes()).Connections);
    }
    
    [HttpGet("Map/{airport}")]
        public ActionResult<AirportConnectionDto> GetNetworkMap(string airport)
        {
            return Ok(new NetworkMapDto(networkedRouteService
                .GetNetworkedRoutesFrom(airport)).Connections
                .SingleOrDefault(c => c.Airport.AirportCode
                    .Equals(airport)));
        }
}