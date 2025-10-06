using backend.Core.Infrastructure.Caching;
using backend.Domain.flight.Entity;
using backend.Domain.flight.Repository;

namespace backend.Domain.flight.Service;

public class FlightService(FlightRepository flightRepository)
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
}