using backend.Domain.flight.Service;
using Microsoft.AspNetCore.Mvc;

namespace backend.Domain.flight.Controller;

[ApiController]
[Route("api/[controller]")]
public class FlightController(FlightService flightService) : ControllerBase
{
    
}