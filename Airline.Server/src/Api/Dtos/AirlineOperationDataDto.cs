using Airline.Server.Domain.airline.Entities;

namespace Airline.Server.Api.Dtos;

public class AirlineOperationDataDto
{
    public int FlightsCurrentlyScheduled { get; set; }
    public int PlanesCurrentlyFlying { get; set; }
    public double TotalMilesFlown { get; set; }
    public int TotalFlightsCompleted { get; set; }

    public AirlineOperationDataDto(AirlineOperationData data)
    {
        FlightsCurrentlyScheduled = data.FlightsCurrentlyScheduled;
        PlanesCurrentlyFlying = data.PlanesCurrentlyFlying;
        TotalMilesFlown = data.TotalMilesFlown;
        TotalFlightsCompleted = data.TotalFlightsCompleted;
    }
}