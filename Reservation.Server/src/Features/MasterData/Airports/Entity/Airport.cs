using Reservation.Server.Core.Entity;

namespace Reservation.Server.Features.MasterData.Airports.Entity;

public class Airport : BaseEntity
{
    public string Code { get; set; }
    public string Name { get; set; }
    public double Latitude { get; set; }
    public double Longitude { get; set; }
    public string City { get; set; }
    public string State { get; set; }
    public string Country { get; set; }
    //TODO: add Airport Economic Model for pricing
}