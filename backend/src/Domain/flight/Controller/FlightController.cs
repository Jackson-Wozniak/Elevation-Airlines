using backend.Domain.flight.Dto;
using backend.Domain.flight.Service;
using Microsoft.AspNetCore.Mvc;

namespace backend.Domain.flight.Controller;

[ApiController]
[Route("api/[controller]")]
public class FlightController(FlightService flightService) : ControllerBase
{
    [HttpGet]
    public ActionResult<IEnumerable<FlightDto>> GetFlights()
    {
        return Ok(flightService.GetAllFlights().Select(f => new FlightDto(f)));
    }

    [HttpGet("{id:long}")]
    public ActionResult<FlightDto> GetFlight(long id)
    {
        return Ok(new FlightDto(flightService.GetFlightById(id)));
    }
    
    [HttpGet("Plane/{callSign}")]
    public ActionResult<IEnumerable<FlightDto>> GetFlightsByPlane(string callSign)
    {
        return Ok(flightService.GetFlightsByPlane(callSign)
            .Select(f => new FlightDto(f)));
    }
}