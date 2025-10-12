using Airline.Server.Core.Infrastructure.Messaging.Publisher;
using Airline.Server.Domain.flight.Entity;
using Airline.Server.Domain.flight.Message;

namespace Airline.Server.Engine.Service;

public class FlightPublisherService(IPublisher publisher)
{
    private const string Channel = "Flights";
    
    public async Task PublishScheduledFlightAsync(List<Flight> flights)
    {
        foreach (var flight in flights)
        {
            await publisher.PublishAsync(Channel, 
                new FlightMessage(FlightMessageType.Scheduled, flight));
        }
    }
    
    public void PublishScheduledFlight(List<Flight> flights)
    {
        foreach (var flight in flights)
        {
            publisher.Publish(Channel, new FlightMessage(FlightMessageType.Scheduled, flight));
        }
    }
    
    public async Task PublishScheduledFlightAsync(Flight flight)
    {
        await publisher.PublishAsync(Channel, 
            new FlightMessage(FlightMessageType.Scheduled, flight));
    }
}