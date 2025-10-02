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
}