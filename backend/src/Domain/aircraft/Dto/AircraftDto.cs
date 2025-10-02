using backend.Domain.aircraft.Entity;
using backend.Domain.aircraft.Enums;

namespace backend.Domain.aircraft.Dto;

public class AircraftDto
{
    public string Manufacturer { get; set; }
    public string Model { get; set; }
    public int CruiseSpeedKnots { get; set; }
    public int RangeNauticalMiles { get; set; }
    public int PassengerCapacity { get; set; }
    public string Category { get; set; }

    public AircraftDto(Aircraft aircraft)
    {
        Manufacturer = aircraft.Manufacturer.ToName();
        Model = aircraft.Model;
        CruiseSpeedKnots = aircraft.CruiseSpeedKnots;
        RangeNauticalMiles = aircraft.RangeNauticalMiles;
        PassengerCapacity = aircraft.PassengerCapacity;
        Category = aircraft.Category.ToString();
    }
}