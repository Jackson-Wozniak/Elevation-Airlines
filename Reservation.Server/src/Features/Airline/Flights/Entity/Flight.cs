using Reservation.Server.Core.Entity;
using Reservation.Server.Features.Airline.Fleet.Entity;
using Reservation.Server.Features.Airline.Flights.Enum;
using Reservation.Server.Features.MasterData.Airports.Entity;
using Reservation.Server.Features.Reservation.Booking.Entity;

namespace Reservation.Server.Features.Airline.Flights.Entity;

public class Flight : BaseEntity
{
    public long ExternalId { get; set; }
    public Plane Plane { get; set; }
    public Airport Departure { get; set; }
    public Airport Destination { get; set; }
    public double DistanceNauticalMiles { get; set; }
    public FlightStatus Status { get; set; }
    public DateTime BoardingTime { get; set; }
    public DateTime TakeoffTime { get; set; }
    public DateTime ArrivalTime { get; set; }
    public FlightBookingDetails BookingDetails { get; set; }
}