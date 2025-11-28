using Airline.Server.Core.Entity;

namespace Airline.Server.Domain.airline.Entities;

public class AirlineOperationData : BaseEntity
{
    //single row in database to persist stats
    //public new long Id { get; set; } = 1;
    /*
    TODO: Airline Statistics holds the long-term stats of the simulation
        - total flights scheduled/completed
        - total miles flown
        - number of flights to/from hub
        - number of airports visited
        - total money generated
        - etc.
    */
    public int FlightsCurrentlyScheduled { get; set; }
    public int PlanesCurrentlyFlying { get; set; }
    public double TotalMilesFlown { get; set; }
    public int TotalFlightsCompleted { get; set; }

    protected AirlineOperationData() { }

    public static AirlineOperationData Create()
    {
        return new AirlineOperationData
        {
            Id = 1L,
            FlightsCurrentlyScheduled = 0,
            PlanesCurrentlyFlying = 0,
            TotalMilesFlown = 0.0,
            TotalFlightsCompleted = 0
        };
    }
}