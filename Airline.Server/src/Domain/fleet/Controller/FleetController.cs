using Airline.Server.Domain.fleet.Dto;
using Airline.Server.Domain.fleet.Entity;
using Airline.Server.Domain.fleet.Repository;
using Airline.Server.Domain.fleet.Service;
using Microsoft.AspNetCore.Mvc;

namespace Airline.Server.Domain.fleet.Controller;

[ApiController]
[Route("api/[controller]")]
public class FleetController(PlaneRepository planeRepository) : ControllerBase
{
    [HttpGet]
    public ActionResult<IEnumerable<Plane>> GetPlanes()
    {
        return Ok(planeRepository.GetPlanes().Select(p => new PlaneDto(p)));
    }
    
    [HttpGet("{callSign}")]
    public ActionResult<Plane> GetPlane(string callSign)
    {
        return Ok(new PlaneDto(planeRepository.GetPlane(callSign)));
    }
}