using Reservation.Server.Core.Entity;
using Reservation.Server.Features.MasterData.Aircraft.Entity;

namespace Reservation.Server.Features.Airline.Fleet.Entity;

public class Plane : BaseEntity
{
    public long ExternalId { get; set; }
    public string CallSign { get; set; }
    public AircraftModel AircraftModel { get; set; }
}