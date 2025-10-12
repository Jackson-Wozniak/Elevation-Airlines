using Reservation.Server.Core.Entity;

namespace Reservation.Server.Features.Airline.Flights.Entity;

public class ScheduledFlight : BaseEntity
{
    public long ExternalId { get; set; }
}