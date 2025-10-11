using Airline.Server.Domain.aircraft.Dto;
using Airline.Server.Domain.fleet.Entity;

namespace Airline.Server.Domain.fleet.Dto;

public class PlaneDto
{
    public string CallSign { get; set; }
    public AircraftDto Aircraft { get; set; }
    public string Status { get; set; }

    public PlaneDto(Plane plane)
    {
        CallSign = plane.CallSign;
        Aircraft = new AircraftDto(plane.Aircraft);
        Status = plane.Status.ToString();
    }
}