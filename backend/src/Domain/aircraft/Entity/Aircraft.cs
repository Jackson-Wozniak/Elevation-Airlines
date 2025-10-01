using backend.Core.Entity;
using backend.Domain.aircraft.Enums;

namespace backend.Domain.aircraft.Entity;

public class Aircraft : BaseEntity
{
    public ManufacturerType Manufacturer { get; set; }
    public string Model { get; set; } = "";
    public int CruiseSpeedKnots { get; set; }
    public int RangeNauticalMiles { get; set; }
    public int PassengerCapacity { get; set; }
    public AircraftCategoryType Category { get; set; }
    
    public Aircraft() { }

    public Aircraft(ManufacturerType manufacturer, string model, 
        int cruiseSpeedKnots, int rangeNauticalMiles, 
        int passengerCapacity, AircraftCategoryType category)
    {
        Manufacturer = manufacturer;
        Model = model;
        CruiseSpeedKnots = cruiseSpeedKnots;
        RangeNauticalMiles = rangeNauticalMiles;
        PassengerCapacity = passengerCapacity;
        Category = category;
    }
}