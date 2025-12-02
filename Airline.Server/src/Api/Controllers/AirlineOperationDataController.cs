using Airline.Server.Api.Dtos;
using Airline.Server.Domain.airline.Services;
using Microsoft.AspNetCore.Mvc;

namespace Airline.Server.Api.Controllers;

[ApiController]
[Route("api/[controller]")]
public class AirlineOperationDataController(
    AirlineOperationDataService airlineOperationDataService) : ControllerBase
{
    [HttpGet]
    public IActionResult GetAirlineOperationData()
    {
        return Ok(new AirlineOperationDataDto(
            airlineOperationDataService.FindAirlineOperationData()));
    }
}