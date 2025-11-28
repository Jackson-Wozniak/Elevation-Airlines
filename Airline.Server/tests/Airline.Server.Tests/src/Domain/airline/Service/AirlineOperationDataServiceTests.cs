using Airline.Server.Core.Data;
using Airline.Server.Domain.airline.Entities;
using Airline.Server.Domain.airline.Repositories;
using Airline.Server.Domain.airline.Services;
using Microsoft.EntityFrameworkCore;

namespace Airline.Server.Tests.Domain.airline.Service;

public class AirlineOperationDataServiceTests
{
    private static AirlineOperationDataService New()
    {
        return new AirlineOperationDataService(
            new AirlineOperationDataRepository(new ApplicationDbContext(
                new DbContextOptionsBuilder<ApplicationDbContext>()
                    .UseInMemoryDatabase(Guid.NewGuid().ToString()).Options)));
    }
    
    [Fact]
    public void Find_NotCreated_ReturnsDefaultValues()
    {
        var service = New();

        AirlineOperationData data = service.FindAirlineOperationData();

        Assert.NotNull(data);
        Assert.Equal(1, data.Id);
        Assert.Equal(0, data.FlightsCurrentlyScheduled);
        Assert.Equal(0, data.PlanesCurrentlyFlying);
        Assert.Equal(0, data.TotalFlightsCompleted);
        Assert.Equal(0, data.TotalMilesFlown);
    }

    [Fact]
    public void CompletedFlight_SingleCompleted_UpdatesValues()
    {
        var service = New();
        const double miles = 10.0;
        service.CompletedFlight(miles);
        
        AirlineOperationData data = service.FindAirlineOperationData();
        
        Assert.NotNull(data);
        //completing flight decrements currently flown plane count
        Assert.Equal(-1, data.PlanesCurrentlyFlying);
        Assert.Equal(miles, data.TotalMilesFlown);
        Assert.Equal(1, data.TotalFlightsCompleted);
    }
    
    [Fact]
    public void CompletedFlight_MultipleCompleted_UpdatesValues()
    {
        var service = New();
        double miles = 100.0;
        double totalMiles = miles;
        service.CompletedFlight(miles);
        
        AirlineOperationData data = service.FindAirlineOperationData();
        
        Assert.NotNull(data);
        //completing flight decrements currently flown plane count
        Assert.Equal(-1, data.PlanesCurrentlyFlying);
        Assert.Equal(totalMiles, data.TotalMilesFlown);
        Assert.Equal(1, data.TotalFlightsCompleted);
        
        miles = 1000.0;
        totalMiles += miles;
        service.CompletedFlight(miles);
        
        data = service.FindAirlineOperationData();
        
        Assert.NotNull(data);
        //completing flight decrements currently flown plane count
        Assert.Equal(-2, data.PlanesCurrentlyFlying);
        Assert.Equal(totalMiles, data.TotalMilesFlown);
        Assert.Equal(2, data.TotalFlightsCompleted);
        
        miles = 50.0;
        totalMiles += miles;
        service.CompletedFlight(miles);
        totalMiles += miles;
        service.CompletedFlight(miles);
        totalMiles += miles;
        service.CompletedFlight(miles);
        
        data = service.FindAirlineOperationData();
        
        Assert.NotNull(data);
        //completing flight decrements currently flown plane count
        Assert.Equal(-5, data.PlanesCurrentlyFlying);
        Assert.Equal(totalMiles, data.TotalMilesFlown);
        Assert.Equal(5, data.TotalFlightsCompleted);
    }

    [Fact]
    public void ScheduledFlight_Single_UpdatesValues()
    {
        var service = New();
        service.ScheduledFlight();
        AirlineOperationData data = service.FindAirlineOperationData();
        Assert.NotNull(data);
        Assert.Equal(1, data.FlightsCurrentlyScheduled);
    }
    
    [Fact]
    public void ScheduledFlights_Multiple_UpdatesValues()
    {
        var service = New();
        service.ScheduledFlights(10);
        AirlineOperationData data = service.FindAirlineOperationData();
        Assert.NotNull(data);
        Assert.Equal(10, data.FlightsCurrentlyScheduled);
    }

    [Fact]
    public void StartedFlight_Multiple_UpdatesValues()
    {
        var service = New();
        service.StartedFlight();
        AirlineOperationData data = service.FindAirlineOperationData();
        Assert.NotNull(data);
        Assert.Equal(-1, data.FlightsCurrentlyScheduled);
        Assert.Equal(1, data.PlanesCurrentlyFlying);
        
        service.StartedFlight();
        data = service.FindAirlineOperationData();
        Assert.NotNull(data);
        Assert.Equal(-2, data.FlightsCurrentlyScheduled);
        Assert.Equal(2, data.PlanesCurrentlyFlying);
        
        service.StartedFlight();
        data = service.FindAirlineOperationData();
        Assert.NotNull(data);
        Assert.Equal(-3, data.FlightsCurrentlyScheduled);
        Assert.Equal(3, data.PlanesCurrentlyFlying);
    }
}