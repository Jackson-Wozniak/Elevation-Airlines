using backend.Domain.fleet.Dto;
using backend.Domain.fleet.Entity;
using backend.Domain.fleet.Repository;
using backend.Domain.fleet.Service;
using Microsoft.AspNetCore.Mvc;

namespace backend.Domain.fleet.Controller;

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