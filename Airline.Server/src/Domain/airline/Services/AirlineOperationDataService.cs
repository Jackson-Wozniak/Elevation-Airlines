using Airline.Server.Domain.airline.Entities;
using Airline.Server.Domain.airline.Repositories;

namespace Airline.Server.Domain.airline.Services;

public class AirlineOperationDataService(
    AirlineOperationDataRepository airlineOperationDataRepository)
{
    public AirlineOperationData FindAirlineOperationData()
    {
        return airlineOperationDataRepository.Find();
    }

    public void CompletedFlight(double miles)
    {
        AirlineOperationData data = FindAirlineOperationData();
        data.TotalMilesFlown += miles;
        data.PlanesCurrentlyFlying -= 1;
        data.TotalFlightsCompleted += 1;
        airlineOperationDataRepository.Update(data);
    }
    
    public void StartedFlight()
    {
        AirlineOperationData data = FindAirlineOperationData();
        data.FlightsCurrentlyScheduled -= 1;
        data.PlanesCurrentlyFlying += 1;
        airlineOperationDataRepository.Update(data);
    }

    public void ScheduledFlight()
    {
        AirlineOperationData data = FindAirlineOperationData();
        data.FlightsCurrentlyScheduled += 1;
        airlineOperationDataRepository.Update(data);
    }
    
    public void ScheduledFlights(int count)
    {
        AirlineOperationData data = FindAirlineOperationData();
        data.FlightsCurrentlyScheduled += count;
        airlineOperationDataRepository.Update(data);
    }
}