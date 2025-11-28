using Airline.Server.Core.Infrastructure.Caching;
using Airline.Server.Domain.flight.Entity;
using Airline.Server.Domain.flight.Enum;
using Airline.Server.Domain.flight.Repository;

namespace Airline.Server.Domain.flight.Service;

public class FlightService(FlightRepository flightRepository, 
    ILogger<FlightService> logger)
{
    private readonly ConcurrentEntityCache<Flight> _flightCache = new(50);
    
    public void DeleteAllFlights()
    {
        flightRepository.DeleteAll();
    }

    public List<Flight> GetFlightsByPlane(string callSign)
    {
        return GetAllFlights()
            .Where(f => f.Plane.CallSign.Equals(callSign)).ToList();
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

    public List<Flight> SaveFlights(List<Flight> flights)
    {
        return flightRepository.SaveAll(flights);
    }

    public Flight UpdateFlightStatus(long id, FlightStatus status)
    {
        //if flight is completed the plane may be sent to maintenance
        try
        {
            //TODO: this is the method that will call AirlineStatisticsService
            var flight = flightRepository.GetById(id);
            flight.FlightStatus = status;
            //if status is completed, we will need to convert this to a FlightRecord
            flightRepository.Update(flight);
            return flight;
        }
        catch (Exception ex)
        {
            logger.LogWarning($"Cannot find flight {id} to update status");
            throw;
        }
    }
}