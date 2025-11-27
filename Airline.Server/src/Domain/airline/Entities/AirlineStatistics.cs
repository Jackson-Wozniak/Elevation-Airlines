using Airline.Server.Core.Entity;

namespace Airline.Server.Domain.airline.Entities;

public class AirlineStatistics : BaseEntity
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
}