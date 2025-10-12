using System.Text.Json;
using Airline.Server.Core.Infrastructure.Messaging.Message;
using Airline.Server.Domain.fleet.Dto;
using Airline.Server.Domain.flight.Dto;
using Airline.Server.Domain.flight.Entity;

namespace Airline.Server.Domain.flight.Message;

public class FlightMessage : IMessage
{
    public FlightMessageType MessageType { get; set; }
    public long Id { get; set; }
    public string Status { get; set; }
    public PlaneDto Plane { get; set; }
    public FlightPlanDto FlightPlan { get; set; }
    public DateTime BoardingTime { get; set; }
    public DateTime TakeoffTime { get; set; }
    public DateTime ExpectedArrivalTime { get; set; }

    public FlightMessage(FlightMessageType type, Flight flight)
    {
        MessageType = type;
        Id = flight.Id;
        Status = flight.FlightStatus.ToString();
        Plane = new PlaneDto(flight.Plane);
        FlightPlan = new FlightPlanDto(flight.FlightPlan);
        BoardingTime = flight.BoardingTime;
        TakeoffTime = flight.TakeoffTime;
        ExpectedArrivalTime = flight.ExpectedArrivalTime;
    }

    public string Serialize()
    {
        return JsonSerializer.Serialize(this);
    }
}