using backend.Core.Infrastructure.Caching;
using backend.Domain.flight.Entity;
using backend.Domain.flight.Enum;
using backend.Domain.flight.Repository;

namespace backend.Domain.flight.Service;

public class FlightService(FlightRepository flightRepository, 
    ILogger<FlightService> logger)
{
    private readonly ConcurrentEntityCache<Flight> _flightCache = new(50);
    
    public void DeleteAllFlights()
    {
        flightRepository.DeleteAll();
    }

    public List<Flight> GetAllFlights()
    {
        return flightRepository.GetAll();
    }

    public Flight GetFlightById(long id)
    {
        return flightRepository.GetById(id);
    }

    public List<Flight> GetFlightsByDate(DateOnly date)
    {
        return GetAllFlights()
            .Where(f => f.StartsOnDate(date))
            .ToList();
    }

    public void UpdateFlightStatus(long id, FlightStatus status)
    {
        //if flight is completed the plane may be sent to maintenance
        try
        {
            var flight = flightRepository.GetById(id);
            flight.FlightStatus = status;
            //if status is completed, we will need to convert this to a FlightRecord
            flightRepository.Update(flight);
        }
        catch (Exception ex)
        {
            logger.LogWarning($"Cannot find flight {id} to update status");
        }
    }
}