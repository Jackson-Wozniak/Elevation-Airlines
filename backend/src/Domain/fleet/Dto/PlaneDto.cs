using backend.Domain.aircraft.Dto;
using backend.Domain.fleet.Entity;

namespace backend.Domain.fleet.Dto;

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