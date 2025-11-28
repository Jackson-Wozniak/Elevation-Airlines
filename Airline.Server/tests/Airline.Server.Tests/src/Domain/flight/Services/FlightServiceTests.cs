using System.Numerics;
using Airline.Server.Core.Data;
using Airline.Server.Domain.aircraft.Entity;
using Airline.Server.Domain.aircraft.Enums;
using Airline.Server.Domain.airline.Repositories;
using Airline.Server.Domain.airline.Services;
using Airline.Server.Domain.airport.Entity;
using Airline.Server.Domain.fleet.Enums;
using Airline.Server.Domain.flight.Entity;
using Airline.Server.Domain.flight.Repository;
using Airline.Server.Domain.flight.Service;
using Airline.Server.Domain.shared;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using Plane = Airline.Server.Domain.fleet.Entity.Plane;

namespace Airline.Server.Tests.Domain.flight.Services;

public class FlightServiceTests
{
    private readonly ILogger<FlightService> _logger = LoggerFactory.Create(builder =>
    { builder.AddConsole(); }).CreateLogger<FlightService>();
    
    private static ApplicationDbContext InMemoryContext()
    {
        return new ApplicationDbContext(
            new DbContextOptionsBuilder<ApplicationDbContext>()
                .UseInMemoryDatabase(Guid.NewGuid().ToString()).Options);
    }

    private readonly Airport TestAirport1 = new Airport("Test1", "Test1", 
        100.0, 100.0, "CITY", "STATE", "USA", 0.0);
    private readonly Airport TestAirport2 = new Airport("Test2", "Test2", 
        100.0, 100.0, "CITY", "STATE", "USA", 0.0);

    [Fact]
    public void DeleteAllFlights_Run_ClearsDatabase()
    {
        var flight = Flight.Create(new Route(TestAirport1, TestAirport2),
            new Plane("ELEV100", new Aircraft(ManufacturerType.Airbus,
                "Test", 100, 1000,
                100, AircraftCategoryType.NarrowBodyAirliner),
                PlaneStatus.Parked),
            DateTime.Now, DateTime.Now);
        var flight2 = Flight.Create(new Route(TestAirport1, TestAirport2),
            new Plane("ELEV100", new Aircraft(ManufacturerType.Airbus,
                    "Test", 100, 1000,
                    100, AircraftCategoryType.NarrowBodyAirliner),
                PlaneStatus.Parked),
            DateTime.Now, DateTime.Now);
        var context = InMemoryContext();
        var airlineService = new AirlineOperationDataService(
            new AirlineOperationDataRepository(context));
        var flightRepo = new FlightRepository(context);
        var service = new FlightService(flightRepo, airlineService, _logger);

        flightRepo.Save(flight);
        flightRepo.Save(flight2);
        
        Assert.Equal(2, service.GetAllFlights().Count);
        service.DeleteAllFlights();
        Assert.Empty(service.GetAllFlights());
    }
}