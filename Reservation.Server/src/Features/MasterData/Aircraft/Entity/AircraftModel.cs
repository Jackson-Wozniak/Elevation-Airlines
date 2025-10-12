using Reservation.Server.Core.Entity;
using Reservation.Server.Features.MasterData.Aircraft.Enums;

namespace Reservation.Server.Features.MasterData.Aircraft.Entity;

public class AircraftModel : BaseEntity
{
    public AircraftManufacturer Manufacturer { get; set; }
    public string Name { get; set; }
    //TODO: add seating arrangements
}