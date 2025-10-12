using Reservation.Server.Core.Entity;
using Reservation.Server.Features.Reservation.Booking.Enum;

namespace Reservation.Server.Features.Reservation.Booking.Entity;

public class AvailableSeat : BaseEntity
{
    public FlightBookingDetails FlightBookingDetails { get; set; }
    public int RowNumber { get; set; }
    public int SeatNumber { get; set; }
    public TicketClass Class { get; set; }
    public decimal Price { get; set; }
}