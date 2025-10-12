using Reservation.Server.Core.Entity;
using Reservation.Server.Features.Airline.Flights.Entity;
using Reservation.Server.Features.Reservation.Booking.Enum;
using Reservation.Server.Features.Users.Entity;

namespace Reservation.Server.Features.Reservation.Booking.Entity;

public class Ticket : BaseEntity
{
    public decimal Price { get; set; }
    public User User { get; set; }
    public AvailableSeat Seat { get; set; }
    public Flight Flight { get; set; }
}