using Reservation.Server.Core.Entity;
using Reservation.Server.Features.Airline.Flights.Entity;

namespace Reservation.Server.Features.Reservation.Booking.Entity;

public class FlightBookingDetails : BaseEntity
{
    //Seating arrangements, available tickets, pricing, etc.
    public Flight Flight { get; set; }
    public decimal BaseTicketPrice { get; set; }
    public double DemandPercentile { get; set; }
    public List<AvailableSeat> AvailableSeats { get; set; }
    public List<Ticket> SoldTickets { get; set; }
}